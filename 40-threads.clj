;; Make a thread
(defn do-something-in-a-thread []
  (println "Hello from the thread.")
  (println "Good bye from the thread."))

(def the-thread (Thread. do-something-in-a-thread))

;; Run it
(.start the-thread)

;; Print two messages with a three second pause in the middle
;; Between first and second println the REPL is free to use for other tasks
(defn do-something-else []
  (println "Hello from the thread.")
  (Thread/sleep 3000)
  (println "Good bye from the thread."))

(.start (Thread. do-something-else))
