(ns app.db
  (:require [next.jdbc :as jdbc]))

(def ds (atom nil))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn setup-datasource [db-spec]
  (if (= @ds nil)
    (swap! ds (fnil jdbc/get-datasource db-spec))
    @ds))
