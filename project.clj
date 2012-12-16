(defproject cljode "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.4.0"]
    [ring "1.0.2"]
    [compojure "1.0.1"]
    [simpledb "0.1.4"]
    [clj-time "0.3.7"]
    [sandbar/sandbar "0.4.0-SNAPSHOT"]
    [org.markdownj/markdownj "0.3.0-1.0.2b4"]]
  :ring {:handler cljode.core/app}
  :main cljode.core)
