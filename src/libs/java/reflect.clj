(ns libs.java.reflect)

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

(defn call [obj method-name & args]
  (if-let (m find-applicable-method (class obj)
             (name method-name)
             args)

    )
  )