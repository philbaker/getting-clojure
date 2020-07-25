;; eval turns data structures into action. It takes data that you pass in
;; (which looks like clojure code) and compiles and runs it as clojure code

;; A three element list
(def a-data-structure '(+ 2 2))

(eval a-data-structure) ; 4

;; Bind some-data to a list
(def some-data
  '(defn print-greeting [preferred-customer]
     (if preferred-customer (println "Welcome back!"))))
;; at this point we only have data - no print-greeting function
(eval some-data) ; print-greeting is now defined
(print-greeting true) ; Welcome back!
(eval 55) ;; 55
(eval :hello) ; :hello
(eval "hello") ; hello

(def title "For Whom the Bell Tolls")
(def the-symbol 'title) ; title
(eval the-symbol) ; For Whom the Bell Tolls
(eval '(count title)) ; 23

(def fn-name 'print-greeting)
(def args (vector 'preferred-customer))
(def the-println (list 'println "Welcome back!"))
(def body (list 'if 'preferred-customer the-println))

(eval (list 'defn fn-name args body)) ; #'user/print-greeting
(eval (list 'print-greeting true)) ; Welcome back!

(ns codetool.core
  (:require [clojure.java.io :as io]))

(defn read-source [path]
  (with-open [r (java.io.PushbackReader. (io/reader path))]
    (loop [result []]
      (let [expr (read r false :eof)]
        (if (= expr :eof)
          result
          (recur (conj result expr)))))))

;; Our very own repl
(defn my-repl []
  (loop []
    (println (eval (read)))
    (recur)))

;; An Eval of your own
(defn reval [expr]
  (cond
    (string? expr) expr
    (keyword? expr) expr
    (number? expr) expr
    (symbol? expr) (eval-symbol expr)
    (vector? expr) (eval-vector expr)
    (list? expr) (eval-list expr)
    :else :completely-confused))

(defn eval-symbol [expr]
  (.get (ns-resolve *ns* expr)))

(defn eval-vector [expr]
  (vec (map reval expr)))

(defn eval-list [expr]
  (let [evaled-items (map reval expr)
        f (first evaled-items)
        args (rest evaled-items)]
    (apply f args)))

(reval "Hello world") ; "Hello world"
(reval 55) ; 55
(reval :george) ; :george
