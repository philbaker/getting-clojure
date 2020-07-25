;; if statement
(defn print-greeting [preferred-customer]
  (if preferred-customer
    (println "Welcome back to Blotts Books!")
    (println "Welcome to Blotts Books!"))) ; else

(defn print-alternative-greeting [preferred-customer]
  (if preferred-customer
    "So nice to have you back!"))

(defn shipping-charge [preferred-customer order-amount]
  (if preferred-customer
    0.00
    (* order-amount 0.10)))

;; equality
(= 1 1) ; true
(= 2 (+ 1 1)) ; true
(= "Anna Karenina" "Jane Eyre") ; false
(= "Emma" "Emma") ; true
(= (+ 2 2) 4 (/ 40 10) (* 2 2) (- 5 1)) ; true
(= 2 2 2 2 2 2 2 2 2) ; true
(= 2 2 2 2 3 2 2 2 2 2) ; false
(not= "Anna Karenina" "Jane Eyre") ; true
(not= "Anna Karenina" "Anna Karenina") ; false

;; operators
(if (> a b)
  (println "a is bigger than b"))

(if (< bc)
  (println "b is smaller than c"))

(number? 1984) ; true
(number? "Anna Karenina") ; false
(string? "Anna Karenina") ; true
(keyword? "Anna Karenina") ; false
(keyword? :anna-karenina) ; true
(map? :anna-karenina) ; false
(map? {:title 1984}) ; true
(vector? 1984) ; false
(vector? [1984]) ; true

;; Charge extra if it's an express order or oversized
;; and they are not a preferred customer.

(defn shipping-surcharge? [preferred-customer express oversized]
  (and (not preferred-customer) (or express oversized)))
