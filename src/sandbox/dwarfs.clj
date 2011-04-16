(ns sandbox.dwarfs)

(defn random-permutation [n]
  (loop [drawn-nums []
         remaining-nums (apply hash-set (range n))]
    (if (empty? remaining-nums)
      drawn-nums
      (let [next-num (rand-nth (seq remaining-nums))]
        (recur (conj drawn-nums next-num)
               (disj remaining-nums next-num))))))

(defn cycle-length [permutation start-index]
  (loop [len   1
         index start-index]
    (let [next-index (permutation index)]
      (if (= next-index start-index)
        len
        (recur (inc len)
               next-index)))))

(defn dwarfs-survive? [n]
  (let [permutation (random-permutation n)]
    (not (some #(> (cycle-length permutation %)
                   50)
               (range n)))))

(defn simulate [sims]
  (/ (reduce + (repeatedly sims #(if (dwarfs-survive? 100)
                                   1
                                   0)))
     sims))

