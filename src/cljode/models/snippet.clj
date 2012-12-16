(ns cljode.models.snippet
  (:require [simpledb.core :as db]))

(defn get-snippet-value [m value]
  (into {} (for [[k v] m] [(:id v) (get v value)])))

(defn titles []
  (get-snippet-value (db/get :snippet) "title"))

(defn next-id []
  (str (db/update! :next-snippet-id inc)))

(defn id->snippet [id]
  (db/get-in :snippet [id]))

(defn prepare-new [{:keys [title tags syntax code] :as snippet}]
  (let [id (next-id)]
    (-> snippet
      (assoc :id id))))

(defn edit-url [id]
  (str "/edit_snippet/" id))

;; TODO

(defn valid? [{:keys [title tags syntax code]}]
  (= true true))

;; Operations

(defn add! [snippet]
  (when (valid? snippet)
    (let [{:keys [id title tags syntax] :as final} (prepare-new snippet)]
      (db/update! :snippet assoc id final)
      (db/update! :snippet-ids conj id)
      (db/persist-db))))

(defn edit! [snippet]
  (let [id (snippet "id")]
	  (db/update! :snippet dissoc (snippet "code") id)
    (add! (dissoc snippet "id"))))

(defn init! []
  (db/put! :next-snippet-id -1)
  (db/put! :snippet-ids (list))
  (db/put! :snippet {}))
