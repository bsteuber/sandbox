(ns go.topology)

(defprotocol Topology
  (all-points [t])
  (on-board? [t point])
  (neighbours [t point]))

(extend-protocol Topology
  Long
  (all-points [size]
              (for [x (range 1 (inc size))
                    y (range 1 (inc size))]
                [x y]))
  (on-board? [size [x y]]
             (and (<= 1 x size)
                  (<= 1 y size)))
  (neighbours [size [x y]]
              (filter #(on-board? size %)
                      [[x (dec y)]
                       [x (inc y)]
                       [(dec x) y]
                       [(inc x) y]])))

