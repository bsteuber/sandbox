(ns libs.log)

(def log println)

(defn warn [& args]
  (apply log "Warning:" args))