;; complement wraps the function you supply with a
;; call to not, producing a new function that is the
;; complement of the original
(defn adventure? [book]
  (when (= (:genre book) :adventure)
    book))

(defn horror? [book]
  (when (= (:genre book) :horror)
    book))

(defn not-adventure? [book] (not (adventure? book))) ; true
(def not-adventure? (complement adventure?)) ; true

;; every-pred combines predicate functions into a 
;; single function and 'ands' them all together
(def cheap-horror? (every-pred marginally-cheap? horror?)) ; true

(def cheap-horror-possession?
  (every-pred
    marginally-cheap?
    horror?
    (fn [book] (= (:title book) "Possession")))) ; false
