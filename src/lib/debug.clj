(ns lib.debug)

(defmacro dbg [x]
  `(let [x# ~x]
     (prn '~x "=" x#)
     x#))