(def the-result (promise))
(deliver the-result "Emma")
;; The value in my promise is Emma
(println "The value in my promise is" (deref the-result))
;; equivalent with @ shorthand
(println "The value in my promise is" @the-result)

(def inventory [{:title "Emma" :sold 51 :revenue 255}
                {:title "2001" :sold 17 :revenue 170}])

(defn sum-copies-sold [inv]
  (apply + (map :sold inv)))

(defn sum-revenue [inv]
  (apply + (map :revenue inv)))

(let [copies-promise (promise)
      revenue-promise (promise)]
  (.start (Thread. #(deliver copies-promise (sum-copies-sold inventory))))
  (.start (Thread. #(deliver revenue-promise (sum-revenue inventory))))
  (println "The total number of books sold is" @copies-promise) ;; 68
  (println "The total revenue is" @revenue-promise)) ; 425

;; A future is a promise that brings along its own thread
(def revenue-future
  (future (apply + (map :revenue inventory))))

(println "The total revuenue is" @revenue-future) ; 425
