(ns go.protocols)

(defprotocol Board
  (color [board point])
  (play  [board point player]))

(defprotocol Graph
  (get-node [graph id])
  (set-node [graph id node])
  (remove-node [graph id])
  (add-link [graph id-1 id-2])
  (remove-link [graph id-1 id-2])
  (link-seq [graph id]))

(defrecord SimpleGraph [nodes links]
  Graph
  (get-node
   [_ id]
   (nodes id))
  (set-node
   [_ id node]
   (SimpleGraph. (assoc nodes id node)
                 links))
  (remove-node
   [_ id]
   (SimpleGraph. (dissoc nodes id)
                 links))
  (add-link
   [_ id-1 id-2]
   (SimpleGraph. nodes
                 (update-in links [id-1] #(if %
                                            (conj % id-2)
                                            #{id-2}))))
  (remove-link
   [_ id-1 id-2]
   (SimpleGraph. nodes
                 (update-in links [id-1] disj id-2)))
  (link-seq
   [_ id]
   (links id)))

