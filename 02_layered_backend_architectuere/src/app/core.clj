(ns app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [app.cross-cutting.error-handling :refer [error-handler-middleware]]
            [app.cross-cutting.logging :refer [request-logging-middleware]]
            [app.user.routes :refer [user-routes]]))

(def app
  (-> user-routes
      (wrap-json-body {:keywords? true})
      request-logging-middleware
      error-handler-middleware
      wrap-json-response))


(defn -main []
  (run-jetty app {:port 3000 :join? false}))
