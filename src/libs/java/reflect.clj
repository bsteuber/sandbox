(ns libs.java.reflect
  (:use (libs debug seq)
        (libs.java gen)))

(defn find-methods [class pred]
  (filter pred (.getMethods class)))

(defn find-method [class pred]
  (let [methods (find-methods class pred)]
    (if (one-element? methods)
      (first methods))))

(defn applicable-to? [method args]
  true)                                 ; TODO

(defn find-applicable-method [class name args]
  (some #(when
             (and (= (.getName %)
                     name)
                  (applicable-to? % args))
           %)
        (.getMethods class)))

(defn call-method [obj method-name & args]
  (if-let [m (find-applicable-method (class obj)
                                     (java-name method-name)
                                     args)]
    (.invoke m obj (to-array args))
    (error "No applicable method found")))

(defn call-getter [obj slot-name]
  (call-method obj
        (str "get-" (name slot-name))))

(defn call-setter [obj slot-name value]
  (call-method obj
        (str "set-" (name slot-name))
        value))
