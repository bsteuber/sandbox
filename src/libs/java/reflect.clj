(ns libs.java.reflect
  (:use (libs debug seq)
        (libs.java gen)))

(defn find-methods [class pred]
  (filter pred (.getMethods class)))

(defn find-method [class pred]
  (one-element (find-methods class pred)
               (str "Matching methods in class " class)))

(defn applicable-to? [method name args]
  (and (= (.getName method)
          name)
       (= (seq (.getParameterTypes method))
          (seq (map class args)))))

(defn find-applicable-method [class name args]
  (find-method class
               #(applicable-to? % name args)))

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

(defn call-constructor [clazz & args]
  (let [constructor (->> args
                         (map class)
                         (into-array Class)
                         (.getConstructor clazz))]
    (.newInstance constructor (to-array args))))