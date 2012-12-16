(ns cljode.models
  (:require [simpledb.core :as db]
            [cljode.models.snippet :as snippet]))

(defn initialize []
  (db/init)
  (when-not (db/get :snippet)
    (snippet/init!)))
