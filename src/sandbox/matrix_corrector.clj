(ns sandbox.matrix-corrector
  "Helper script for correcting an exercise about elementary matrix multiplications.")

(defn scalar-prod [v1 v2]
  (apply + (map * v1 v2)))

(defn rows [matrix]
  (partition 3 matrix))

(defn cols [matrix]
  (apply map
         list
         (rows matrix)))

(defn mult3d [m1 m2]
  (for [r (rows m1)
        c (cols m2)]
    (scalar-prod r c)))

(def ** #(reduce mult3d %&))

(defn make-matrix [f]
  (for [i (range 1 4)
        j (range 1 4)]
    (f i j)))

(def unit-matrix
  (make-matrix #(if (= %1 %2)
                  1
                  0)))

(defn s [i lambda]
  (make-matrix #(if (= %1 %2)
                  (if (= %1 i)
                    lambda
                    1)
                  0)))

(defn q [i j lambda]
  (make-matrix #(if (= %1 %2)
                  1
                  (if (= [i j] [%1 %2])
                    lambda
                    0))))

(defn p [i j]
  (let [k (first (disj #{1 2 3} i j))]
    (make-matrix #(if (#{[k k]
                         [i j]
                         [j i]}
                       [%1 %2])
                    1
                    0))))

(def pr-len 6)

(defn print-matrix [m]
  (prn)
  (doseq [row (partition 3 m)]
    (doseq [elt row]
      (let [s (str elt)
            s1 (if (.startsWith s "-")
                 s
                 (str " " s))
            space-count (- pr-len (count s1))
            filled-s (apply str s1 (repeat space-count " "))]
        (print filled-s)))
    (prn)))

(defn pri [x]
  (print-matrix x)
  x)

(def a [ 3   -1  6
        -2   2/3 4
        1/2 -1/6 0])

(defn check [s t]
  (println "\n\n\n\n\n\n=====\nStart\n=====")
  (println "\n\n==========\nS-Schritte\n==========")
  (let [after-s (reduce (fn [m last-s]
                          (pri (** last-s m)))
                        a (reverse s))
        _ (println "\n==========\nT-Schritte\n==========")
        after-t (reduce (fn [m first-t]
                          (pri (** m first-t)))
                        after-s t)]
    (println "\n========\nS-Matrix\n========")
    (print-matrix (apply ** s))
    (println "\n========\nT-Matrix\n========")
    (print-matrix (apply ** t))
    (println  (if (= after-t [1 0 0
                              0 1 0
                              0 0 0])
                "\nKorrekt :)"
                "\nFalsch :("))))


(check [
        (q 3 2 3/8)
        (q 2 1 4/3)
        (q 3 1 -1)
        (s 2 2)
        (s 3 6)
        ]
       [
        (q 2 1 3)
        (p 1 3)
        (p 1 2)
        (q 1 2 6)
        (s 1 -1)
        (s 2 1/16)
        ]
       )