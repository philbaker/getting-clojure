;; You can define anonymous functions using fn
(fn [n] (* 2 n))

(println "A function:" (fn [n] (* 2 n)))
(def double-it (fn [n] (* 2 n)))
(double-it 10) ; 20
((fn [n] (* 2 n)) 10) ; 20

(fn [book]
  (when (<= (:price book) 9.99)
    book))

(defn cheaper-f [max-price]
  (fn [book]
    (when (<= (:price book) max-price)
      book)))

(def real-cheap? (cheaper-f 1.00))
(def kind-of-cheap? (cheaper-f 1.99))
(def marginally-cheap? (cheaper-f 5.99))

(real-cheap? dracula) ; nil
(kind-of-cheap? dracula) ; truthy
(marginally-cheap? dracula) ; truthy

(defn both-f [predicate-f-1 predicate-f-2]
  (fn [book]
    (when (and (predicate-f-1 book) (predicate-f-2 book))
      book)))

(def cheap-horror? (both-f cheap? horror?))
(def real-cheap-adventure? (both-f real-cheap? adventure?))
(def real-cheap-horror? (both-f real-cheap? horror?))

(def cheap-horror-possession?
  (both-f cheap-horror?
    (fn [book] (= (:title book) "Possession"))))
