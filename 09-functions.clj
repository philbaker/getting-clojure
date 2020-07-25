(defn greet
  ([to-whom] (println "Welcome to Blotts Books" to-whom))
  ([message to-whom] (println message to-whom))) 

(greet "Dolly") ; Welcome to Blotts Books Dolly
(greet "Howdy" "Stranger") ; Howdy Stranger

(defn greet
  ([to-whom] (greet "Welcome to Bloggs Books" to-whom))
  ([message to-whom] (println message to-whom)))

(defn print-any-args [& args]
  (println "My arguments are:" args))
(print-any-args 7 true nil) ; My arguments are: (7 true nil)

(defn first-argument [& args]
  (first args))
(first-argument "cheese" 55 "lucozade") ; cheese

(defn new-first-argument [x & args] x)
(new-first-argument "test" "George") ; test
