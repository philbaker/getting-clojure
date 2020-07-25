;; In clojure all everything other than false or nil return true
(if 1
  "I like science fiction"
  "I like mysteries!")

(if "hello"
  "I like science fiction"
  "I like mysteries!")

(if [1 2 3]
  "I like science fiction"
  "I like mysteries!")


(if 0 "yes" "no") ; => "yes"
(if 1 "yes" "no") ; => "yes"
(if 1.0 "yes" "no") ; => "yes"
(if :russ "yes" "no") ; => "yes"
(if "Russ" "yes" "no") ; => "yes"
(if "true" "yes" "no") ; => "yes"
(if "false" "yes" "no") ; => "yes"
(if "nil" "yes" "no") ; => "yes"

(if [] (println "An empty vector is true!"))
(if [1 2 3] (println "So is a populated vector"))
(if {} (println "An empty map is true!"))
(if {:title "Make Room! Make Room!"}
  (println "A full map is true!"))
(if () (println "An empty list is true!"))
(if '(:full :list) (println "A full list is true!"))

                
