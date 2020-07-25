(def books
  [{:title "Jaws" :copies-sold 2000000}
   {:title "Emma" :copies-sold 3000000}
   {:title "2001" :copies-sold 4000000}])

;; Initial attempt at recursion - works but the stack will run out of space
;; when there are lots of books
(defn sum-copies
  ([books] (sum-copies books 0))
  ([books total]
   (if (empty? books)
     total
     (sum-copies
       (rest books)
       (+ total (:copies-sold (first books)))))))

;; This example takes advantage of tail call optimisation (using recur)
(defn sum-copies [books]
  (loop [books books total 0]
    (if (empty? books)
      total
      (recur
        (rest books)
        (+ total (:copies-sold (first books)))))))
      
;; there is usually a better way to do things than a loop though
(defn sum-copies [books] (apply + (map :copies-sold books)))
