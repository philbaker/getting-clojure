(defn shipping-charge [preferred-customer order-amount]
  (if preferred-customer
    0.0
    (if (< order-amount 50.0)
      5.0
      (if (< order-amount 100.0)
        10.0
        (* 0.1 order-amount)))))

;; The cond expression is a useful way to avoid nested if statements
(defn shipping-charge [preferred-customer order-amount]
  (cond
    preferred-customer 0.0
    (< order-amount 50.0) 5.0
    (< order-amount 100.0) 10.0
    :else (* 0.1 order-amount))) ; :else is a convention - it evaluates to true. you could use :anything and it would work in the same way

(defn customer-greeting [status]
  (case status
    :gold       "Welcome, welcome, welcome back!!!"
    :preferred  "Welcome back!"
                "Welcome back to Blotts Books"))

