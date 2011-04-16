(ns go.ascii)

(def char->col {\x :black
                \o :white})

(def col->char {:black \x
                :white \o
                nil    \.})

(defn read-board
  "Read a board from a string like \n...\n.xo\n.o."
  [s]
  (let [lines (-> s
                  str/trim
                  (str/replace " " "")
                  str/split-lines)
        size (count lines)]
    (into (make-board size)
          (filter second
                  (for [[x y :as p] (points size)]
                    [p (char->col (nth (nth lines (dec y))
                                       (dec x)))])))))

(defn format-board
  "Formats a board to a string like \n...\n.xo\n.o."
  [{size :size :as board}]
  (str "\n"
       (str/join "\n" (for [y (coords size)]
                             (str/join (for [x (coords size)]
                                         (col->char (board [x y]))))))
       "\n"))