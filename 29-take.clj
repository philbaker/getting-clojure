(sort-by :rating books)

(reverse (sort-by :rating books))

(take 3 (reverse (sort-by :rating books))) ; ({:title "Emma", :price 7.99, :genre :comedy, :rating 9} {:title "Dracula", :price 1.99, :genre :horror, :rating 7} {:title "Deep Six", :price 13.99, :genre :sci-fi, :rating 6})

(map :title (take 3 (reverse (sort-by :rating books)))) ; ("Emma" "Dracula" "Deep Six")

(interpose
  " // "
  (map :title (take 3 (reverse (sort-by :rating books))))) ; ("Emma" " // " "Dracula" " // " "Deep Six")

(defn format-top-titles [books]
  (apply
    str
    (interpose
      " // "
      (map :title (take 3 (reverse (sort-by :rating books)))))))

(format-top-titles books) ; "Emma // Dracula // Deep Six"

;; same as above but using nested expression to make things more readable
(defn format-top-titles [books]
  (->>
    books
    (sort-by :rating)
    reverse
    (take 3)
    (map :title)
    (interpose " // ")
    (apply str)))
