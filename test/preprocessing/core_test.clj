(ns preprocessing.core-test
  (:use (preprocessing core)
        [lazytest.describe :only (describe it)]))

(describe "Generate"
  (it "function calls"
    (= (snippet (line 0 0 100 100))
       "line(0, 0, 100, 100);"))
  (it "operators"
    (= (snippet (* 1 2)
                (* 1 2 3)
                (< 1 2)
                (< 1 2 3)
                (++ x))
       "(1 * 2);\n(1 * 2 * 3);\n(1 < 2);\n((1 < 2) && (2 < 3));\n(x ++);"))
  (it "var definitions"
    (= (snippet (int board-size 9))
       "int boardSize = 9;"))
  (it "for loops"
    (= (snippet (for [(int i 0)
                      (< i 5)
                      (++ i)]
                  (line i 0 100 100)))
       "for (int i = 0; (i < 5); (i ++)) {\n  line(i, 0, 100, 100);\n}"))
  (it "if")
  (it "do")
  (it "classes")
  (it "when")
  (it "unless"))


