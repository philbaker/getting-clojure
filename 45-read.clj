;; Clojure data literals are essentially the same as clojure code:
;; Data - note the quote
'(helvetica times-roman [comic-sans]
  (futura gil-sans
    (courier "All the fonts I have loved!")))

;; more data
'(defn print-greeting [preferred-customer]
   (if preferred-customer
     (println "Welcome back!")))

;; turn the data into code by simply removing the quote
(defn print-greeting [preferred-customer]
  (if preferred-customer
    (println "Welcome back!")))

;; Clojure uses read to read data
;; It will return a four-element list (data) that looks very similar to code below
(read)
(defn print-greeting [preferred-customer]
  (if preferred-customer (println "Welcome back!")))

;; read-string parses the characters in a string into a clojure value
(def s
  "(defn print-greeting [preferred-customer]
    (if preferred-customer (println \"Welcome Back!\")))")
(read-string s) ; returns a four element list
