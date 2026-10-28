(ns app.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [app.user.routes :refer [user-routes]]))

(defn -main []
  (run-jetty user-routes {:port 3000 :join? false}))
