(ns preprocessing.examples
  (:use (preprocessing core)))

(defsnippet line
  (int x 100)
  (line 0 0 x x))

(defsnippet moving-line
  (void (setup)
        (background 150))
  (void (draw)
        (line 0 0 mouseX mouseY))
  (void (mouseClicked)
        (background 150)))

(defsnippet for-loop
  (for [(int x 0)
        (< x 5)
        (++ x)]
    (int y (* 20 x))
    (line 0 y 100 y)))

(defsnippet go-lib
  (size 500 500)
  (background "#B7740F")
  (stroke 0)
  (int board-size 9)
  (int go-margin 50)
  (int first-coord go-margin)
  (int guessed-last-coord (- width go-margin))
  (int go-pixels (cast int
                       (/ (- guessed-last-coord first-coord)
                          (- board-size 1))))
  (int stone-radius (- go-pixels 1))
  (int (coord2pixel x)
       (return (+ first-coord
                  (* (- x 1)
                     go-pixels))))
  (int last-coord (coord2pixel board-size))
  (void (draw-board)
        (for [(int i 1)
              (<= i board-size)
              (++ i)]
          (int pix (coord2pixel i))
          (line first-coord pix last-coord pix)
          (line pix first-coord pix last-coord)))
  (void (draw-stone x y)
        (ellipse (coord2pixel x)
                 (coord2pixel y)
                 stone-radius
                 stone-radius))
  (void (add-black x y)
        (fill 0)
        (draw-stone x y))
  (void (add-white x y)
        (fill 255)
        (draw-stone x y)))

(def go-pos
  (snippet* `(~go-lib
              (void (setup)
                    (no-loop))
              (void (draw)
                    (draw-board)
                    (add-black 3 4)
                    (add-white 6 6)))))


(def spit-out
  #(spit (str "/Users/ben/Sites/preprocessing/examples/" %1 ".pde")
         %2))

(spit-out "line" line)
(spit-out "moving-line" moving-line)
(spit-out "go" go-pos)
