(println "Hello, world!") ; Say hi.

(str "Clo" "jure") ; returns "Clojure"
(str "Hello," " " "world" "!") ; returns the string "Hello, world!"

(count "Hello, world") ; returns 12
(count "") ; returns 0

(println true) ; prints true
(println false) ; prints false
(println nil) ; prints nil

(+ 1900 84) ; evaluates to 1984
(- 2000 16) ; evaluates to 1984
(* 16 124) ; evaluates to 1984
(/ 25792 13) ; evaluates to 1984
(/ (+ 1984 2010) 2) ; evaluates to 1997
(+ 1000 500 500 1) ; evaluates to 2001
(- 2000 10 4 2) ; evaluates to 1984
(/ (+ 1984.2 2010.0) 2.0) ; evaluates to 1997.1
(/ 8 3) ; evaluates to 8/3
(quot 8 3) ; evaluates to 2
(+ 1984 2010.0) ; evaluates to 3994.0

(def the-average (/ (+ 20 40.0) 2.0))

(defn say-welcome [what] 
  (println "Welcome to" what))

(defn average [a b]
  (/ (+ a b) 2.0))
