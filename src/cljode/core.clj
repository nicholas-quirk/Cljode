(ns cljode.core
  (:use compojure.core
        ring.util.response
        ring.adapter.jetty
        cljode.routes
        ring.middleware.params)
  (:require [compojure.handler :as handler]
            [compojure.response :as response]
            [cljode.models :as models]))

(def app (-> main-routes wrap-params))

(defn -main [& args]
  (models/initialize)
  (let [port (if (= (count args) 1) (first args) 8080)]
    (run-jetty app {:port (Integer. port)})))
