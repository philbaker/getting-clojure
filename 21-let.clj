;; let binds names to values in a purely local and temporary way. It gives
;; you the advantage of named variables without creating side effects

;; This function works but its intention is not clear. It could be improved
;; with variables 
(defn compute-discount-amount [amount discount-percent min-charge]
  (if (> (* amount (- 1.0 discount-percent)) min-charge)
    (* amount (- 1.0 discount-percent))
    min-charge))

;; Same example using let
(defn compute-discount-amount [amount discount-percent min-charge]
  (let [discounted-amount (* amount (- 1.0 discount-percent))]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))

;; Bind multiple names inside single let
(defn compute-discount-amount [amount discount-percent min-charge]
  (let [discount (* amount discount-percent)
        discounted-amount (- amount discount)]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))
    

;; Multiple expressions in let body
(defn compute-discount-amount [amount discount-percent min-charge]
  (let [discount (* amount discount-percent)
        discounted-amount (- amount discount)]
    (println "Discount:" discount)
    (println "Discounted amount" discounted-amount)
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))

(def user-discounts
  {"Nicholas" 0.10 "Jonathan" 0.07 "Felicia" 0.05})

;; this approach works but you have to carry user-name
(defn compute-discount-amount [amount user-name user-discounts min-charge]
  (let [discount-percent (user-discounts user-name)
        discount (* amount discount-percent)
        discounted-amount (- amount discount)]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))

;; this approach makes use of a higher level function
(defn mk-discount-price-f [user-name user-discounts min-charge]
  (let [discount-percent (user-discounts user-name)]
    (fn [amount]
      (let [discount (* amount discount-percent)
            discounted-amount (- amount discount)]
        (if (> discounted-amount min-charge)
          discounted-amount
          min-charge)))))

;; Get a price function for Felicia
(def compute-felicia-price (mk-discount-price-f "Felicia" user-discounts 10.0))

;; and sometime later compute a price
(compute-felicia-price 20.0)

;; let relies on lexical scope
;; we can use the title inside of the let
(let [title "Let's pretend this never happened"]
  (println "The title is" title)
  (print-the-title))

;; but now we're outside of the let
(defn print-the-title []
  (println "The title is" title)) ; this will fail - 'Unable to resolve symbol: title in this context'

;; If you nest let expressions, then a binding in a let can mask a binding
;; in an outer let
(let [title "Pride and prejudice"]
  (let [title "Sense and sensibility"]
    (println title)))  ;; Sense and sensibility

;; you can also override a binding inside of the same let:
(let [title "Pride and prejudice"
      title (str title " and zombies")]
  (println title)) ;; Pride and prejudice and zombies
