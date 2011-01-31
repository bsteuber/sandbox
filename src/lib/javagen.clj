(ns lib.javagen
  "Utilities for generating Java classes from clojure"
  (:use (lib string))
  (:require [clojure.string :as str]))

(def first-lower #(vary-first % str/lower-case))

(defn java-name
  [s]
  (->> (.split s "-")
       (map capitalize)
       str/join
       first-lower))

(def class-name
  (comp capitalize java-name))

(def constant-name
  (comp str/upper-case  
        #(.replace % "-" "_")))

(def getter-name
  (comp #(str "get" %)
        class-name))

(def setter-name
  (comp #(str "set" %)
        class-name))

(defn clojure-prefix
  [s & [prefix]]
  (str (or prefix "-")
       s))