(ns app.user.service
  (:require [app.user.repository :as repo]
            [app.user.validation :as validation]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn get-user [input]
  (-> input
      validation/validate-user-id
      repo/fetch-user))
