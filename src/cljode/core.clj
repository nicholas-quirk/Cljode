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

(defn -main [port]
  (models/initialize)
  (run-jetty app {:port (Integer. port)}))
