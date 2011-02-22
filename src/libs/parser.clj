(ns lib.parser
  (:refer-clojure :as c))

(defn return
  "A parser that always succeeds and returns res without consuming any input."
  [res])

(defn fail
  "A parser that fails with the given error message."
  [error])

(defn bind
  "A parser that feeds the result of parser p into function f."
  [p f])

(defmacro let
  "Like clojure's let, but all binding right handed sides must be parsers
   that will be consecutively applied."
  [bindings result])

(def empty
  "A parser that only succeeds when the input stream is empty.")

(def first
  "A parser that consumes the first token of the input stream.")        

(defn take
  "A parser that consumes the first n tokens of the input stream."
  [n])

(defn choice
  ""
  [options])

(defn not [p])

(defn or [& ps])

(defn and [& ps])

(defn surround [p before after])

(defn repeat [p n])

(defn range [p min max])

(defn many
  ([p]
     (many p 0 nil))
  ([p min]
     (many p min nil))
  ([p min max]))

(defn many1
  ([p]     (many p 1 nil))
  ([p max] (many p 1 max)))

(defn skip-many [p])