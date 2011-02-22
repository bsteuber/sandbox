(ns libs.java.wrap
  (:use (libs debug))
  (:require [clojure.string :as str]))

(defmulti manipulate (fn [obj action arg]
                       [(class obj)
                        action]))

(defmethod manipulate [Object :get] [obj _ key]
           )

(defmacro make [class & kw-args]
  )

(defn clojure-name [s]
  (->> s
       (re-seq #"[A-Z]?[a-z]*")
       butlast
       (map (memfn toLowerCase))
       (str/join "-")))

(def getters {})
(def setters {})

(defn match-prefix [s prefix]
  (when
      (and (.startsWith s prefix)
           (Character/isUpperCase (nth s (count prefix))))
    (clojure-name (.substring s (count prefix)))))

(defn getter [s]
  (or (match-prefix s "get")
      (match-prefix s "is")))

(defn setter [s]
  (match-prefix s "set"))

(defmacro wrap [class]
  `(do ~@(for [m (.getMethods (resolve class))]
           (let [name        (.getName m)
                 param-types (.getParameterTypes m)
                 return-type (.getReturnType m)
                 arity       (count param-types)
                 getter      (getter name)
                 setter      (setter name)
                 bool?       (or (and getter
                                      (= (str return-type)
                                         "boolean"))
                                 (and setter
                                      (= (str (first param-types))
                                         "boolean")))
                 key         (keyword (str (or getter setter)
                                           (when bool? "?")))
                 method      (symbol (str "." name))]
             (cond (and getter (= 0 arity))
                   `(alter-var-root #'getters
                                    assoc-in
                                    [~class ~key]
                                    #(~method %))
                   (and setter (= 1 arity))
                   `(alter-var-root #'setters
                                    assoc-in
                                    [~class ~key]
                                    #(~method %1 %2)))))))

(defn get-slot [obj key]
  (let [getter (get-in getters [(class obj) key])]
    (if getter
      (getter obj)
      (error "Getter not found:" key "for class" (class obj)))))

(defn set-slot [obj key value]
  (let [setter (get-in setters [(class obj) key])]
    (if setter
      (setter obj value)
      (error "Setter not found:" key "for class" (class obj)))))

(defn update-slots [obj updates]
  (doseq [[k v] updates]
    (set-slot obj k v)))