(def jack "All work and no play makes Jack a dull boy.")
(def repeated-text (repeat jack)) ; "All work and no play makes Jack a dull boy."
(nth repeated-text 10) ; same as above
(nth repeated-text 10202) ; same as above
(take 20 repeated-text) ; above x20

(take 7 (cycle [1 2 3])) ; (1 2 3 1 2 3 1)

;; iterate returns a sequence whose first element is the value passed in
(def numbers (iterate inc 1))
(first numbers) ; 1
(nth numbers 1) ; 2
(nth numbers 2) ; 3
(nth numbers 99) ; 100
(take 5 numbers) ; (1 2 3 4 5)
