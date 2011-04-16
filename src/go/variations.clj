(ns go.variations)

(defrecord Node [parents children])

(defrecord Tree [root lookup])

(defn make-node []
  (atom (Node. [] [])))

(defn add-link [parent child]
  (swap! parent update-in [:children] conj child)
  (swap! child update-in [:parents] conj parent))

(defn find-node [{:keys [lookup]} position]
  (@lookup position))

(defn add-node [tree position]
  (let [node (make-node)]
    (swap! (:lookup tree)
           (assoc position node))))

(defn make-tree [root-position]
  (let [root (make-node)]
    (Tree. root {root-position root})))

