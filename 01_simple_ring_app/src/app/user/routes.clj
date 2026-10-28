(ns app.user.routes
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [app.user.service :as service]))

(defroutes user-routes
  (GET "/" []
    {:status 200
     :body "User service"})
  (GET "/users/:id" [id]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/write-str (service/build-user id))})
  (route/not-found {:status 404
                    :body "Not found"}))
