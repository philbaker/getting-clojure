;; complement wraps the function you supply with a
;; call to not, producing a new function that is the
;; complement of the original
(defn adventure? [book]
  (when (= (:genre book) :adventure)
    book))

(defn not-adventure? [book] (not (adventure? book))) ; true
(def not-adventure? (complement adventure?)) ; true
