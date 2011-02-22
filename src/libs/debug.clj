(ns libs.debug
  (:require [clojure.string :as str]))

(defmacro dbg [x]
  `(let [x# ~x]
     (prn '~x "=" x#)
     x#))

(defn error [& args]
  (throw (RuntimeException. (str/join " " args))))