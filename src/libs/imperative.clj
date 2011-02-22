(ns libs.imperative)

(defn domap [f seq]
  (doseq [elt seq]
    (f elt)))