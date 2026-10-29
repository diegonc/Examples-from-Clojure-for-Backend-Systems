(ns app.user.controller
  (:require [app.user.service :as service]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn get-user-controller [request]
  (let [id (get-in request [:route-params :id])
        result (service/get-user {:id id})]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body {:status "success"
            :data result
            :error nil}}))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn create-user-controller [request]
  (let [name (-> request :body :name)
        result (service/create-user {:name name})]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body {:status "success"
            :data result
            :error nil}}))
