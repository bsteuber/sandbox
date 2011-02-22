(ns libs.seq)

(defn one-element? [seq]
  (and (first seq)
       (not (next seq))))

(defn one-element
  ([seq] (one-element seq "Sequence"))
  ([seq descr]
     (if (one-element? seq)
       (first seq)
       (error descr "should contain only one element!\n" seq))))