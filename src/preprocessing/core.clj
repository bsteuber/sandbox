(ns preprocessing.core
  (:use (libs codegen
              debug
              javagen))
  (:require [clojure.string :as str]))

(declare gen-cmds gen-expr)

(defn gen-funcall [[fname & args]]
  (->> args
       (map gen-expr)
       comma-sep*
       parens
       (str fname)))

(defn gen-block [header cmds] 
  (str header
       " {"
       (indent2 (str "\n"(gen-cmds cmds)))
       "\n}"))

(defn gen-defn [[typename fcall & cmds]]
  (gen-block (words typename
                    (gen-funcall fcall))
             cmds))

(defn gen-for [[_ bindings & cmds]]
  (let [decl (->> bindings                  
                  (map gen-expr)
                  (str/join "; ")
                  parens)]    
    (gen-block (words "for" decl)
               cmds)))

(defn gen-def [[typename varname expr]]
  (words typename
         varname
         "="
         (gen-expr expr)))

(defn gen-comparision-op [[op & args]]
  (let [compare-exprs (->> args
                           (partition 2 1)
                           (map #(gen-binary-op (cons op %))))]
    (if (= 1 (count compare-exprs))
      (first compare-exprs)
      (gen-binary-op (cons "&&"
                           compare-exprs)))))

(defn gen-postfix-op [[op x]]
  (parens (words x (gen-expr op))))

(defn gen-cast [[_ typename expr]]
  (words (parens typename)
         (gen-expr expr)))

(defn gen-return [[_ expr]]
  (words "return" (gen-expr expr)))

(def specials
  {"for"    gen-for
   "return" gen-return})

(defn gen-expr [expr]  
  (if (sequential? expr)
    (let [[first-token] expr]
      (cond
       (= "cast" first-token)         (gen-cast expr)
       (comparision-op? first-token)  (gen-comparision-op expr)
       (binary-op? first-token)       (gen-binary-op expr)
       (postfix-op? first-token)      (gen-postfix-op expr)
       (primitive-type? first-token)  (gen-def expr)
       :default                       (gen-funcall expr)))
    expr))

(defn gen-cmd [expr]
  (let [[first-token second-token] expr]
    (cond
     (specials first-token)             ((specials first-token) expr)
     (and (primitive-type? first-token)
          (sequential? second-token))   (gen-defn expr)
          :default                      (str (gen-expr expr) ";"))))

(defn gen-cmds [exprs]
  (->> exprs
       (map gen-cmd)
       (str/join "\n")))

(defn snippet* [args]
  (-> args
      canonize
      gen-cmds))

(defmacro snippet [& args]
  `(snippet* '[~@args]))

(defmacro defsnippet [name & args]
  `(def ~name
     (snippet ~@args)))