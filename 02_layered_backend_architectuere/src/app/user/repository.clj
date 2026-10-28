(ns app.user.repository)

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn fetch-user [{:keys [id]}]
  ;; simulate database
  {:id id :name "Alice"})
