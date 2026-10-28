(ns app.user.routes
  (:require [clojure.data.json :as json]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [app.user.controller :as controller]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defroutes user-routes
  (GET "/" []
    {:status 200
     :body "User service"})
  (GET "/users/:id" request
    (json/write-str (controller/get-user-controller request)))
  (route/not-found {:status 404
                    :body "Not found"}))
