(ns app.cross-cutting.error-handling)

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn error-handler-middleware [handler]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        {:status 500
         :body {:status "error" :message (.getMessage e)}}))))
