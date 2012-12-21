(ns cljode.views.common
  (use hiccup.core
       hiccup.page-helpers))

(def includes {:codemirror-js (include-js "/js/codemirror-3.0/lib/codemirror.js")
               :codemirror-css (include-css "/js/codemirror-3.0/lib/codemirror.css")
               :clike (include-js "/js/codemirror-3.0/mode/clike/clike.js")
               :clojure (include-js "/js/codemirror-3.0/mode/clojure/clojure.js")
               :coffeescript (include-js "/js/codemirror-3.0/mode/coffeescript/coffeescript.js")
               :commonlisp (include-js "/js/codemirror-3.0/mode/commonlisp/commonlisp.js")
               :css (include-js "/js/codemirror-3.0/mode/css/css.js")
               :diff (include-js "/js/codemirror-3.0/mode/diff/diff.js")
               :ecl (include-js "/js/codemirror-3.0/mode/ecl/ecl.js")
               :erlang (include-js "/js/codemirror-3.0/mode/erlang/erlang.js")
               :gfm (include-js "/js/codemirror-3.0/mode/gfm/gfm.js")
               :go (include-js "/js/codemirror-3.0/mode/go/go.js")
               :groovy (include-js "/js/codemirror-3.0/mode/groovy/groovy.js")
               :haskell (include-js "/js/codemirror-3.0/mode/haskell/haskell.js")
               :haxe (include-js "/js/codemirror-3.0/mode/haxe/haxe.js")
               :htmlembedded (include-js "/js/codemirror-3.0/mode/htmlembedded/htmlembedded.js")
               :htmlmixed (include-js "/js/codemirror-3.0/mode/htmlmixed/htmlmixed.js")
               :http (include-js "/js/codemirror-3.0/mode/http/http.js")
               :javascript (include-js "/js/codemirror-3.0/mode/javascript/javascript.js")
               :jinja2 (include-js "/js/codemirror-3.0/mode/jinja2/jinja2.js")
               :less (include-js "/js/codemirror-3.0/mode/less/less.js")
               :lua (include-js "/js/codemirror-3.0/mode/lua/lua.js")
               :markdown (include-js "/js/codemirror-3.0/mode/markdown/markdown.js")
               :mysql (include-js "/js/codemirror-3.0/mode/mysql/mysql.js")
               :ntriples (include-js "/js/codemirror-3.0/mode/ntriples/ntriples.js")
               :ocaml (include-js "/js/codemirror-3.0/mode/ocaml/ocaml.js")
               :pascal (include-js "/js/codemirror-3.0/mode/pascal/pascal.js")
               :perl (include-js "/js/codemirror-3.0/mode/perl/perl.js")
               :php (include-js "/js/codemirror-3.0/mode/php/php.js")
               :pig (include-js "/js/codemirror-3.0/mode/pig/pig.js")
               :plsql (include-js "/js/codemirror-3.0/mode/plsql/plsql.js")
               :properties (include-js "/js/codemirror-3.0/mode/properties/properties.js")
               :python (include-js "/js/codemirror-3.0/mode/python/python.js")
               :r (include-js "/js/codemirror-3.0/mode/r/r.js")
               :rpm (include-js "/js/codemirror-3.0/mode/rpm/rpm.js")
               :rst (include-js "/js/codemirror-3.0/mode/rst/rst.js")
               :ruby (include-js "/js/codemirror-3.0/mode/ruby/ruby.js")
               :rust (include-js "/js/codemirror-3.0/mode/rust/rust.js")
               :scheme (include-js "/js/codemirror-3.0/mode/scheme/scheme.js")
               :shell (include-js "/js/codemirror-3.0/mode/shell/shell.js")
               :sieve (include-js "/js/codemirror-3.0/mode/sieve/sieve.js")
               :smalltalk (include-js "/js/codemirror-3.0/mode/smalltalk/smalltalk.js")
               :smarty (include-js "/js/codemirror-3.0/mode/smarty/smarty.js")
               :sparql (include-js "/js/codemirror-3.0/mode/sparql/sqarql.js")
               :stex (include-js "/js/codemirror-3.0/mode/stex/stex.js")
               :tiddlywiki (include-js "/js/codemirror-3.0/mode/tiddlywiki/tiddlywiki.js")
               :tiki (include-js "/js/codemirror-3.0/mode/tiki/tiki.js")
               :vb (include-js "/js/codemirror-3.0/mode/vb/vb.js")
               :vbscript (include-js "/js/codemirror-3.0/mode/vbscript/vbscript.js")
               :velocity (include-js "/js/codemirror-3.0/mode/velocity/velocity.js")
               :verilog (include-js "/js/codemirror-3.0/mode/verilog/verilog.js")
               :xml (include-js "/js/codemirror-3.0/mode/xml/xml.js")
               :xquery (include-js "/js/codemirror-3.0/mode/xquery/xquery.js")
               :yaml (include-js "/js/codemirror-3.0/mode/yaml/yaml.js")
               :z80 (include-js "/js/codemirror-3.0/mode/z80/z80.js")
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
    (build-head [:default :codemirror-css :codemirror-js (keyword syntax)])
      [:body content]))
