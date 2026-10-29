(ns app.config)

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(def db-spec
  {:dbtype "postgresql"
   :host "localhost"
   :port 5432
   :dbname "app"
   :user "app"
   :password "app"})

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(def environment "development")
