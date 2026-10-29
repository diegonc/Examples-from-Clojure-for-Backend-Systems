(ns app.cross-cutting.error-handling
  (:require [app.config :refer [environment]]))

(defn error-response [message]
  {:status "error"
   :data nil
   :error message})


#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn error-handler-middleware [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        (if (= environment "production")
          {:status 500
           :body (error-response (.getMessage e))}
          (throw e))))))
