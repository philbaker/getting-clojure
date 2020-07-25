;; apply allows you to put a function and the arguments you want to call with it
;; in a collection

;; original
(+ 1 2 3 4) ; 10

;; alternative
(def the-function +)
(def args [1 2 3 4])
(apply the-function args) ; 10

(def v ["The number " 2 "best selling " "book."])

;; More or less the same as:
;; (str "The number " 2 " best selling " "book.")
(apply str v)

;; More or less the same as:
;; (list "The number " 2 " best selling " "book.")
(apply list v)

;; turn back into vector
(apply vector (apply list v))

(def my-inc [n] (+ 1 n))

(def my-inc (partial + 1))

(defn cheaper-than [max-price book]
  (when (<= (:price book) max-price)
    book))

;; A partial partially fills in the arguments for an existing function, 
;; producing a new function of fewer arguments 
(def real-cheap? (partial cheaper-than 1.00)) ; nil
(def kind-of-cheap? (partial cheaper-than 1.99)) ; truthy
(def marginally-cheap? (partial cheaper-than 5.99)) ; truthy
