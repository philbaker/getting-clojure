;; update produces a new map similar to the input map

;; Start with 1,000 copies sold
(def book {:title "Emma" :copies 1000})

;; Now we have 1,001.
(def new-book (update book :copies inc))

;; update-in is useful for nested maps
(def by-author
  {:name "Jane Austen"
   :book {:title "Emma" :copies 1000}})

(def new-by-author (update-in by-author [:book :copies] inc))
