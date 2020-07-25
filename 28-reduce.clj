;; reduce is the basic tool for combining (or reducing) the items of a sequence
;; into a single value

(def numbers [10 20 30 40 50])

(defn add2 [a b]
  (+ a b))

(add2 5 10) ; 15

(reduce add2 0 numbers) ; 150
(reduce + 0 numbers) ; simplified version of above - returns 150
(reduce + numbers) ; initial value starts with first element if omitted - returns 150

;; reduce is not just for adding numbers
;; use reduce to find the highest priced book and return the price
(defn hi-price [hi book]
  (if (> (:price book) hi)
    (:price book)
    hi))

(reduce hi-price 0 books)
