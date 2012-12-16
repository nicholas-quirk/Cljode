(ns cljode.views.common
  (use hiccup.core
       hiccup.page-helpers))

(def includes {:jquery (include-js "/js/jquery-1.7.2.min.js")
               :codemirror-js (include-js "/js/codemirror-3.0/lib/codemirror.js")
               :codemirror-css (include-css "/js/codemirror-3.0/lib/codemirror.css")
               :clojure (include-js "/js/codemirror-3.0/mode/clojure/clojure.js")
               :default (include-css "/css/default.css")})

(defmacro defpartial
  "Create a function that returns html use hiccup. The function is callable with the given name."
  [fname params & body]
    `(defn ~fname ~params
      (html
        ~@body)))

(defpartial build-head [incls]
  [:head
    (html 
      [:title "Cljode"]
      (map #(get includes %) incls))])

(defpartial main-layout [syntax & content]
  (html5
    (build-head [:default :codemirror-css :jquery :codemirror-js (keyword syntax)])
      [:body content]))
