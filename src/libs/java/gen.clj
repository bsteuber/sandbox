(ns libs.java.gen
  "Utilities for generating Java classes from clojure"
  (:use (libs string))
  (:require [clojure.string :as str]))

(defn java-name [s]
  (let [[first-token & tokens] (.split s "-")]
    (str/join (cons first-token
                    (map capitalize tokens)))))

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



