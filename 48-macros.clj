;; Macros give you the ability to transform your code as it gets read in but 
;; before it gets compiled. 

;; You can avoid boilerplate code by rolling repetitive code into a macro.

(defn print-rating [rating]
  (cond
    (pos? rating)  (println "Good book!")
    (zero? rating) (println "Totally indifferent.")
    :else          (println "Run away!")))

(defn arithmetic-if [n pos zero neg]
  (cond
    (pos? n) pos
    (zero? n) zero
    (neg? n) neg))

(arithmetic-if 0 :great :meh :boring) ; :meh

;; We start to have issues if we try something with side-effects
(defn print-rating [rating]
  (arithmetic-if rating
    (println "Good book!")
    (println "Totally indifferent.")
    (println "Run away!")))

;; Good book!
;; Totally indifferent.
;; Run away!
(print-rating 10) 

(defn arithmetic-if [n pos-f zero-f neg-f]
  (cond
    (pos? n) (pos-f)
    (zero? n) (zero-f)
    (neg? n) (neg-f)))

(defn print-rating [rating]
  (arithmetic-if rating
    #(println "Good book!")
    #(println "Totally indifferent.")
    #(println "Run away!")))

(print-rating 10)

(defn arithmetic-if->cond [n pos zero neg]
  (list 'cond (list 'pos? n) pos
              (list 'zero? n) zero
              :else neg))

(arithmetic-if->cond 'rating
                     '(println "Good book!")
                     '(println "Totally indifferent")
                     '(println "Run away!"))


;; Macros are just functions but the difference is that they are part
;; of the compilation process
(defmacro arithmetic-if [n pos zero neg]
  (list 'cond (list 'pos? n) pos
              (list 'zero? n) zero
              :else neg))

;; with a macro, this line
(arithmetic-if rating :loved-it :meh :hated-it)
;; will be turned into this before it's compiled
(cond (pos? rating) :loved-it (zero? rating) :meh :else :hated-it)

(defmacro print-it [something]
  (list 'println "Something is" something))
;; Because this is a macro it  does not evaluate to 30 because 
;; (println "Something is" (+ 10 20)) is executed first
(print-it (+ 10 20)) ; Something is 30

;; clojure's templating system 
;; set up values
(def n 100)
(def pos "it's positive!")
(def zero "it's zero!")
(def neg "it's negative")

;; plug in the cond
`(cond
   (pos? ~n) ~pos
   (zero? ~n) ~zero
   :else ~neg)

(defmacro arithmetic-if [n pos zero neg]
  `(cond
     (pos? ~n) ~pos
     (zero? ~n) ~zero
     :else ~neg))

(defmacro arithmetic-if [n pos zero neg]
  (list 'cond (list 'pos? n) pos
              (list 'zero? n) zero
              :else neg))

(defmacro our-defn [name args & body]
  `(def ~name (fn ~args ~@body)))

(our-defn add2 [a b] (+ a b))
(add2 2 2) ; 4

;; debugging macros
(macroexpand-1 '(arithmetic-if 100 :pos :zero :neg))
;; (cond (pos? 100) :pos (zero? 100) :zero :else :neg)
