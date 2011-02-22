(ns lib.parser.sgf
  (:require [lib.parser :as p]))

;; (def whitespace
;;   (p/skip-many (p/choice " \n\r\t")))

;; (def surround-whitespace
;;   #(surround % whitespace whitespace))

;; (def parens
;;   #(surround "(" % ")"))

;; (def escaped-closing-bracket
;;   (p/bind "\\]"
;;           (constantly \])))

;; (def not-closing-bracket
;;   (p/and (p/not "]")
;;          p/first))

;; ;; TODO ignore downcase
;; (def attribute-name
;;   (p/many (p/not (p/choice ";[]()"))))

;; (def attribute-value
;;   (-> (p/many (or escaped-closing-bracket
;;                   not-closing-bracket))
;;       (p/surround "[" "]")
;;       surround-whitespace))

;; (def attribute
;;   (p/let [name   attribute-name
;;           values (many1 attribute-value)]
;;     {(keyword (apply str name))
;;      values}))

;; (def node
;;   (p/and whitespace
;;          ";"
;;          whitespace
;;          (p/bind (many attribute)
;;                  #(apply merge %))))

;; (def variation
;;   (-> (p/let [nodes (many1 node)
;;               vars  (many variation)]        
;;         {:nodes nodes :vars vars})
;;       parens
;;       (surround-whitespace)))

;; (def sgf
;;   (p/and variation
;;          p/empty))

;; (defn variation->tree [[node & next-nodes] vars]
;;   (assoc node
;;     :children (if next-nodes
;;                 [(flatten-variation next-nodes vars)]
;;                 (for [{:keys [nodes vars]} vars]
;;                   (flatten-variation nodes vars)))))

;; (defn parse-sgf [string]
;;   (let [{:keys [result error]} (p/parse variation string)]
;;     (if error
;;       (throw (RuntimeException. (str "Parser Error: " error)))
;;       (variation->tree (:nodes result)
;;                        (:vars result)))))
