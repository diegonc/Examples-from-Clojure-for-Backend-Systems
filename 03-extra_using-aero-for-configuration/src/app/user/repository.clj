(ns app.user.repository
  (:require [next.jdbc :as jdbc]
            [app.db :refer [ds]]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn fetch-user [id]
  (jdbc/execute-one!
   @ds
   ["SELECT id, name FROM users WHERE id = ?"
    id]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn create-user [{:keys [name]}]
  (jdbc/with-transaction [tx @ds]
    (jdbc/execute-one!
     tx
     ["INSERT INTO users (name) VALUES (?) RETURNING id"
      name])))
