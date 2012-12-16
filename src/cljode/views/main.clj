(ns cljode.views.main
  (:use hiccup.core
        hiccup.page-helpers
        hiccup.form-helpers)
  (:require [clojure.string :as string]
            [cljode.views.common :as common]
            [cljode.models.snippet :as snippet]))

(defn add-snippet [{:keys [title tags syntax code]}]
  (common/main-layout syntax
    [:div.snippets
	    [:ul.items
	      (for [title (snippet/titles)]
	        [:li (link-to (snippet/edit-url (key title)) (val title))])]]
    [:div.float_left
	    (form-to [:post "/add_snippet"]
	      [:div.label "title"] (text-field :title title) [:br]
	      [:div.label "tags"] (text-field :tags tags) [:br]
	      [:div.label "syntax"] (text-field :syntax syntax) [:br]
	      (text-area {:id "code" :name "code"} :code code) [:br]
	      (submit-button "save"))]))

(defn edit-snippet [{:keys [id]}]
  (if-let [snippet (snippet/id->snippet id)]
    (common/main-layout (snippet "syntax")
      (form-to [:post "/edit_snippet"]
        (hidden-field :id id)
        [:div.label "title"] (text-field :title (snippet "title")) [:br]
        [:div.label "tags"] (text-field :tags (snippet "tags")) [:br]
	      [:div.label "syntax"] (text-field :syntax (snippet "syntax")) [:br]
	      [:div.textarea_border (text-area {:id "code" :name "code"} :code (snippet "code"))] [:br]
        (submit-button "save"))
        [:script {type "text/javascript"} 
          "var editor = CodeMirror.fromTextArea(document.getElementById('code'), {});"])))