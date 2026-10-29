(ns app.cross-cutting.logging)

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn request-logging-middleware [handler]
  (fn [request]
    (let [method (:request-method request)
          uri (:uri request)]
      (println method " " uri)
      (handler request))))
