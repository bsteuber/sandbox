(ns go.board
  (:use (go color topology)))
>
(defprotocol Board
  (move [board point color])
  (remove-stone [board point])) ; also IFn for point lookup

(defn put-stones [board color points]
  (reduce #(assoc %1 %2 color)
          board
          points))

(defn make-board [size & {:keys [black white]}]
  (-> {:size size}
      (put-stones :black black)
      (put-stones :white white)))

(defn remove-chain-when-dead [board point]
  (let [color (board point)
        size (:size board)]
    (loop [todo [point]
           chain []
           seen #{}]
      (if (empty? todo)
        (reduce remove-stone board chain)
        (let [[current-point & rest-todo] todo
              current-color (board current-point)]
          (cond (seen current-point)
                  (recur rest-todo chain seen)
                (nil? current-color)
                  board
                (= color current-color)
                  (recur (distinct (concat rest-todo
                                           (neighbours size current-point)))
                         (conj chain current-point)
                         (conj seen current-point))
                :else
                  (recur rest-todo
                         chain
                         (conj seen current-point))))))))

(defn remove-chains-when-dead [board points]
  (reduce remove-chain-when-dead board points))

(extend-protocol Board
  clojure.lang.APersistentMap
  (remove-stone [board point]
                (dissoc board point))
  (move [board point color]
        (let [size (:size board)]
          (when (and (on-board? size point)
                     (not (board point)))
            (-> board
                (assoc point color)
                (remove-chains-when-dead (filter #(= (board %)
                                                     (other-color color))
                                                 (neighbours size point)))
                (remove-chain-when-dead point))))))