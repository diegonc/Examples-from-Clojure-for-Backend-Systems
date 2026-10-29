(ns app.core
  (:gen-class)
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [app.cross-cutting.error-handling :refer [error-handler-middleware]]
            [app.cross-cutting.logging :refer [request-logging-middleware]]
            [app.db :refer [setup-datasource]]
            [app.user.routes :refer [user-routes]]))

(defn api-handler [opts]
  (-> user-routes
      (wrap-json-body {:keywords? true})
      request-logging-middleware
      (error-handler-middleware (:dev-mode? opts))
      wrap-json-response))


(defn -main []
  (let [env (or (System/getenv "APP_ENV") "dev")
        config (aero/read-config (io/resource "config.edn")
                                 {:profile (keyword env)
                                  :resolver aero/root-resolver})
        handler (api-handler (:app  config))]
    ;; Set timezone of the JVM to workaround PostgreSQL connection issues
    (-> config
        (get-in [:app :tz])
        (or "UTC")
        java.util.TimeZone/getTimeZone
        java.util.TimeZone/setDefault)
    (setup-datasource (get-in config [:app :db-spec]))
    (run-jetty handler {:port (-> config :server :port) :join? false})))
