(def dracula {:title "Dracula"
              :author "Stoker"
              :price 1.99
              :genre :horror})

(defn cheap? [book]
  (when (<= (:price book) 9.99)
    book))

(defn pricey? [book]
  (when (> (:price book) 9.99)
    book))

(cheap? dracula) ; truthy
(pricey? dracula) ; nil

(defn horror? [book]
  (when (= (:genre book) :horror)
    book))

(defn adventure? [book]
  (when (= (:genre book) :adventure)
    book))

(horror? dracula) ; truthy
(adventure? dracula) ; nil

(defn cheap-horror? [book]
  (when (and (cheap? book)
             (horror? book))
    book))

(defn pricey-adventure? [book]
  (when (and (pricey? book)
             (adventure? book))
    book))


(cheap-horror? dracula) ; truthy
(pricey-adventure? dracula) ; nil

(def reasonably-priced? cheap?)
(reasonably-priced? dracula) ; truthy

// Call any function with dracula as the argument
(defn run-with-dracula [f]
  (f dracula))

(run-with-dracula pricey?) ; nil
(run-with-dracula horror?) ; truthy

(defn both? [first-predicate-f second-predicate-f book]
  (when (and (first-predicate-f book)
             (second-predicate-f book))
    book))

(both? cheap? horror? dracula) ; truthy
