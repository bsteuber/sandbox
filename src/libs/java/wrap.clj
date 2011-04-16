(ns libs.java.wrap
  (:use (libs.java reflect)))

(defn set-slots [obj & updates]
  (doseq [[slot-name value] (partition 2 updates)]
    (call-setter obj slot-name value))
  obj)

(defn construct [class & updates]
  (prn class updates)
  (apply set-slots
         (call-constructor class)
         updates))

(defn handle-options [handler-map args]
  (doseq [[key value] (partition 2 args)]
    (when-let [handler (handler-map key)]
      (handler value))))