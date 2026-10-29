(ns app.user.validation)

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn validate-user-id [{:keys [id]}]
  (if (re-matches #"\d+" id)
    {:id (Long/parseLong id)}
    (throw (ex-info "Invalid user id" {:id id}))))
