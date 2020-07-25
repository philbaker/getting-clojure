(defn flavor [x]
  (cond
    (list? x) :list
    (vector? x) :vector
    (set? x) :set
    (map? x) :map
    (string? x) :string
    :else :unknown))

(defmulti my-count flavor)
(defmethod my-count :list [x] (list-specific-count x))
(defmethod my-count :vector [x] (vector-specific-count x))

;; a sequence made from a vector:
(def title-seq (seq ["Emma" "Oliver Twist" "Robinson Crusoe"])) ; (Emma Oliver Twist Robinson Crusoe)

(seq '("Emma" "Oliver Twist" "Robinson Crusoe")) ; ("Emma" "Oliver Twist" "Robinson Crusoe")

;; seq on a map
(seq {:title "Emma", :author "Austen", :published 1815}) ; ([:title "Emma"] [:author "Austen"] [:published 1815])

(seq []) ; nil
(seq '()) ; nil
(seq {}) ; nil

;; get first element
(first (seq '("Emma" "Oliver Twist" "Robinson Crusoe"))) ; "Emma"
;; get rest of elements
(rest (seq '("Emma" "Oliver Twist" "Robinson Cruesoe"))) ; ("Oliver Twist" "Robinson Cruesoe")
;; add element of front of sequence
(cons "Emma" (seq '("Oliver Twist" "Robinson Crusoe"))) ; ("Emma" "Oliver Twist" "Robinson Crusoe")

(defn my-count [col]
  (let [the-seq (seq col)]
    (loop [n 0 s the-seq]
      (if (seq s)
        (recur (inc n) (rest s))
        n))))

;; the following all return a sequence
(rest [1 2 3]) ; (2 3)
(rest {:fname "Jane" :lname "Austen"}) ; ([:lname "Austen"])
(next {:fname "Jane" :lname "Austen"}) ; ([:lname "Austen"])
(cons 0 [1 2 3]) ; (0 1 2 3)
(cons 0 #{1 2 3}) ; (0 1 3 2)

(def titles ["Jaws" "Emma" "2001" "Dracula"])
(sort titles) ; ("2001" "Dracula" "Emma" "Jaws")
(reverse titles) ; ("Dracula" "2001" "Emma" "Jaws")
(reverse (sort titles)) ; ("Jaws" "Emma" "Dracula" "2001")

(def titles-and-authors ["Jaws" "Benchley" "2001" "Clarke"])
(partition 2 titles-and-authors) ; (("Jaws" "Benchley") ("2001" "Clarke"))
