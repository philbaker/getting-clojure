(filter neg? '(1 -22 3 -99 4 5 6 -77)) ; (-22 -99 -77)

(def books
  [{:title "Deep Six" :price 13.99 :genre :sci-fi :rating 6}
   {:title "Dracula" :price 1.99 :genre :horror :rating 7}
   {:title "Emma" :price 7.99 :genre :comedy :rating 9}
   {:title "2001" :price 10.50 :genre :sci-fi :rating 5}])

(defn cheap? [book]
  (when (<= (:price book) 9.99)
    book))

(filter cheap? books) ; ({:title "Dracula", :price 1.99, :genre :horror, :rating 7} {:title "Emma", :price 7.99, :genre :comedy, :rating 9})

(if (some cheap? books)
  (println "We have cheap books for sale!))
