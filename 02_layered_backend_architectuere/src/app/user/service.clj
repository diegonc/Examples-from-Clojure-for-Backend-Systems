(ns app.user.service
  (:require [app.user.repository :as repo]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn get-user [id]
  (repo/fetch-user id))
