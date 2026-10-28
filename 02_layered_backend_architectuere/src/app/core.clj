(ns app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [app.cross-cutting.logging :refer [request-logging-middleware]]
            [app.user.routes :refer [user-routes]]))

(def app
  (-> user-routes
      request-logging-middleware))


(defn -main []
  (run-jetty app {:port 3000 :join? false}))
