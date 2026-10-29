(ns app.cross-cutting.error-handling)

(defn error-response [message]
  {:status "error"
   :data nil
   :error message})


#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn error-handler-middleware [handler opts]
  (fn [request]
    (try
      (handler request)
      (catch Exception e
        (if (not (:dev-mode? opts))
          {:status 500
           :body (error-response (.getMessage e))}
          (throw e))))))
