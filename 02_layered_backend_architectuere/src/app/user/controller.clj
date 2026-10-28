(ns app.user.controller
  (:require [app.user.service :as service]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn get-user-controller [request]
  (let [id (get-in request [:route-params :id])
        result (service/get-user {:id id})]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body result}))
