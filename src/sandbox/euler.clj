(ns sandbox.euler)

(defn divides? [factor n]
  (zero? (rem n factor)))

(defn multiple-of-any? [factors]
  (fn [n]
    (some #(divides? % n)
          factors)))

(defn sum-multiples [factors below-num]
  (->> (range below-num)
       (filter (multiple-of-any? factors))
       (apply +)))

(defn euler-1
  "Add all the natural numbers below one thousand that are multiples of 3 or 5."
  []
  (sum-multiples [3 5] 1000))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn gen-fibs [a b]
  (cons a (lazy-seq (gen-fibs b (+ a b)))))

(def fibs
  (gen-fibs 0 1 ))

(defn euler-2
  "By considering the terms in the Fibonacci sequence whose values do not
   exceed four million, find the sum of the even-valued terms."
  []
  (->> fibs
       (take-while #(<= % 4000000))
       (filter even?)
       (apply +)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn factors [n]
  (->> (range 2 (/ (inc n) 2))
       (filter #(divides? % n))))

(defn prime? [n]
  (and (> n 1)
       (empty? (factors n))))

(defn prime-factors [n]
  (filter prime?
          (factors n)))

(defn euler-3
  "What is the largest prime factor of the number 600851475143?"
  []
  (apply max (prime-factors 600851475143)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def do-3n+1
  (memoize (fn rec [n]
             (cond (= n 1)   0
                   (even? n) (inc (rec (/ n 2)))

                   :else     (inc (rec (inc (* n 3))))))))

(defn euler-14
  []
  "Which starting number, under one million, produces the longest chain?"
  (apply max-key
         do-3n+1
         (range 1 1000000)))
