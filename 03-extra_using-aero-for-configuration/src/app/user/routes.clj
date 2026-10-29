(ns app.user.routes
  (:require [compojure.core :refer [GET defroutes POST]]
            [compojure.route :as route]
            [app.user.controller :as controller]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defroutes user-routes
  (GET "/" []
    {:status 200
     :body "User service"})
  (GET "/users/fail" _
    (throw (Exception. "This endpoint must fail")))
  (POST "/users" request
    (controller/create-user-controller request))
  (GET "/users/:id" request
    (controller/get-user-controller request))
  (route/not-found {:status 404
                    :body "Not found"}))
