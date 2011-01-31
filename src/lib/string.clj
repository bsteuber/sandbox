(ns lib.string
  (:require [clojure.string :as str]))

(defn vary-first [s f]
  (apply str
         (f (first s))
         (rest s)))

(defn capitalize
  "capitalizes the first letter of a string, but leaves the rest intact."
  [s]
  (vary-first s str/upper-case))