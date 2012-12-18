(defproject cljode "1.0.0"
  :description "FIXME: write description"
  :dependencies [
    [org.clojure/clojure "1.4.0"]
    [ring "1.0.2"]
    [compojure "1.0.1"]
    [simpledb "0.1.4"]]
  :ring {:handler cljode.core/app}
  :main cljode.core)
