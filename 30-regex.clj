;; A regular expression that matches Pride and Prejudice followed by anything
(def re #"Pride and Prejudice.*")

;; A string that may or may not match
(def title "Pride and Prejudice and Zombies")

(if (re-matches re title)
  (println "We have a classic!"))

(re-seq #"\w+" title) ; ("Price" "and" "Prejudice" "and" "Zombies")
