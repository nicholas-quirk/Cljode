(ns cljode.views.main
  (:use hiccup.core
        hiccup.page-helpers
        hiccup.form-helpers)
  (:require [clojure.string :as string]
            [cljode.views.common :as common]
            [cljode.models.snippet :as snippet]))

(def syntax-options [
  "clink"
  "clojure"
  "coffeescript"
  "commonlisp"
  "css"
  "diff"
  "ecl"
  "erlang"
  "gfm"
  "go"
  "groovy"
  "haskell"
  "haxe"
  "htmlembedded"
  "htmlmixed"
  "http"
  "javascript"
  "jinja2"
  "less"
  "lua"
  "markdown"
  "mysql"
  "ntriples"
  "ocaml"
  "pascal"
  "perl"
  "php"
  "pig"
  "plsql"
  "properties"
  "python"
  "r"
  "rpm"
  "rst"
  "ruby"
  "rust"
  "scheme"
  "shell"
  "sieve"
  "smalltalk"
  "smarty"
  "sparql"
  "stex"
  "tiddlywiki"
  "tiki"
  "vb"
  "vbscript"
  "velocity"
  "verilog"
  "xml"
  "xquery"
  "yaml"
  "z80"])

(defn build-snippet-links []
    [:div.snippets
      [:ul.items
        (for [title (snippet/titles)]
        [:li (link-to (snippet/edit-url (key title)) (val title))])]])

(defn build-header []
  [:h1 "Cljode"])

(defn add-snippet [{:keys [title tags syntax code]}]
  (common/main-layout syntax
    (build-header)
    (build-snippet-links)
    [:div.float_left
      (form-to [:post "/add_snippet"]
        [:div.label "title"] (text-field :title title) [:br]
        [:div.label "tags"] (text-field :tags tags) [:br]
        [:div.label "syntax"] (drop-down :syntax syntax-options) [:br]
        (text-area {:id "code" :name "code"} :code code) [:br]
        (submit-button "add"))]))

(defn edit-snippet [{:keys [id]}]
  (if-let [snippet (snippet/id->snippet id)]
    (common/main-layout (snippet "syntax")
      (build-header)
      (build-snippet-links)
      [:div.float_left
      (form-to [:post "/edit_snippet"]
        (hidden-field :id id)
        [:div.label "title"] (text-field :title (snippet "title")) [:br]
        [:div.label "tags"] (text-field :tags (snippet "tags")) [:br]
        [:div.label "syntax"]  (drop-down :syntax syntax-options (snippet "syntax")) [:br]
        [:div.textarea_border (text-area {:id "code" :name "code"} :code (snippet "code"))] [:br]
        (submit-button "save"))]
        [:script {type "text/javascript"}
          "var editor = CodeMirror.fromTextArea(document.getElementById('code'), {});"])))
