(ns go.hashing)

(defprotocol BoardLookup
  (find-position [_ board])
  (add-position [_ board node]))