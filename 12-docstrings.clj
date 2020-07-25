;; Comment example
;; Return the average of two parameters
(defn average [a b]
  (/ (+ a b) 2.0))

;; Docstring example
(defn average
  "Return the average of a and b."
  [a b]
  (/ (+ a b) 2.0))
(doc average) ;; Return the average of a and b

(def ISBN-LENGTH "Length of an ISBN code." 13)
(doc ISBN-LENGTH) ;; Length of an ISBN code

;; Multi-arity function
(defn multi-average
  "Return the average of 2 or 3 numbers."
  ([a b]
   (/ (+ a b ) 2.0))
  ([a b c]
   (/ (+ a b c) 3.0)))
