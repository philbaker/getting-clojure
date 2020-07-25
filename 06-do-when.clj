(do
  (println "This is four expressions.")
  (println "All grouped together as one")
  (println "That prints some stuff and then evaluates to 44")
  44)

(defn shipping-charge[preferred-customer order-amount]
  (if preferred-customer
    (do
      (println "Preferred customer, free shipping!")
      0.0)
    (do
       (println "Regular customer, charge them for shipping.")
       (* order-amount 0.10))))

(when preferred-customer
  (println "Hello returning customer!")
  (println "Welcome back to Blotts Books!"))
