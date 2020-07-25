;; A lot of sequences in clojure are lazy meaning that they do not 
;; consume resources until requested. The ultimate 'pay only for what you use' 
;; programming technique

;; take is lazy so defining 1000000000 does not create a billion items
(def many-nums (take 1000000000 (iterate inc 1)))
(println (take 20 (take 1000000000 (iterate inc 1)))) ; (1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20)

(def evens (map #(* 2 %) (iterate inc 1)))
(take 20 evens) ; (2 4 6 8 10 12 14 16 18 20 22 24 26 28 30 32 34 36 38 40)
;; interleave is also lazy
(take 10 (interleave numbers evens)) ; (1 2 2 4 3 6 4 8 5 10)

(def test-books
  [{:author "Bob Jordan", :title "Wheel of Time, Book 1"}
   {:author "Jane Austen", :title "Wheel of Time, Book 2"}
   {:author "Chuck Dickens", :title "Wheel of Time, Book 3"}
   {:author "Leo Tolstoy", :title "Wheel of Time, Book 4"}
   {:author "Bob Poe", :title "Wheel of Time, Book 5"}
   {:author "Jane Jordan", :title "Wheel of Time, Book 6"}
   {:author "Chuck Austen", :title "Wheel of Time, Book 7"}])

(def numbers [1 2 3])
(def trilogy (map #(str "Wheel of Time, Book " % ) numbers)) ; ("Wheel of Time, Book 1" "Wheel of Time, Book 2" "Wheel of Time, Book 3")

(def numbers (iteracte inc 1))
(def titles (map #(str "Wheel of Time, Book " % ) numbers))
(def first-names ["Bob" "Jane" "Chuck" "Leo"])
(cycle first-names)
(def last-names ["Jordan" "Austen" "Dickens" "Tolstoy" "Poe"])
(cycle last-names)
(defn combine-names [fname lname]
  (str fname " " lname))

(def authors
  (map combine-names
    (cycle first-names)
    (cycle last-names)))

(defn make-book [title author]
  {:author author :title title})

(def test-books (map make-book titles authors))

(lazy-seq [1 2 3]) ; (1 2 3)

;; Here we go!
;; [1 2 3]
(defn chatty-vector []
  (println "Here we go!")
  [1 2 3])

;; Here wo go!
;; 1
(def s (lazy-seq (chatty-vector)))

(defn my-repeat [x]
  (cons x (lazy-seq (my-repeat x))))

(def my-iterate [f x]
  (cons x (lazy-seq (my-iterate f (f x)))))

(defn my-map [f col]
  (when-not (empty? col)
    (cons (f (first col))
          (lazy-seq (my-map f (rest col))))))
