(ns cljode.routes
  (:use compojure.core
        hiccup.core
        hiccup.form-helpers)
  (:require [compojure.route :as route]
            [cljode.views.common :as common]
            [cljode.views.main :as main-view]
            [cljode.models.snippet :as snippet]
            [ring.util.response :as response]))

(defn handle-add-snippet-form [params]
  (snippet/add! params)
  (main-view/add-snippet {})
  (response/redirect "/"))

(defn handle-edit-snippet-form [params]
  (snippet/edit! params)
  (response/redirect "/"))

(defroutes main-routes
  (GET "/" [] (main-view/add-snippet {}))
  (GET "/edit_snippet/:id" {params :params} (main-view/edit-snippet params))  
  (POST "/add_snippet" {params :params} (handle-add-snippet-form params))
  (POST "/edit_snippet" {params :params} (handle-edit-snippet-form params))  
  (route/resources "/"))
