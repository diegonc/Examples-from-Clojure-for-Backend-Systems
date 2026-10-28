(ns app.db
  (:require [next.jdbc :as jdbc]
            [app.config :refer [db-spec]]))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(def ds (jdbc/get-datasource db-spec))
