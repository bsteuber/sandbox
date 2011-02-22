(ns libs.string
  (:require [clojure.string :as str]))

(defn vary-first-char [s f]
  (apply str
         (f (first s))
         (rest s)))

(defn capitalize
  "capitalizes the first letter of a string, but leaves the rest intact."
  [s]
  (vary-first-char s str/upper-case))

(def upper-case? #(Character/isUpperCase %))