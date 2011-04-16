(ns go.position
  (:use (go core board)))

(defprotocol Play
  (play [game point]))

(defrecord Position [board next-color]
  Play
  (play [position point]
        (let [{:keys board next-color} position]
          (Position. (move board next-color)
                     (other-color next-color)))))

(defn make-position [board next-color]
  (Position. board next-color))