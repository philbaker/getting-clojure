(def anonymous-book
  {:title "Sir Gawan and the Green Knight"})

(def with-author
  {:title "Once and Future King" :author "White"})

(defn uppercase-author [book]
  (let [author (:author book)]
    (if author
      (.toUpperCase author))))

;; Does the same as above but if-let makes it more succinct
(defn uppercase-author [book]
  (if-let [author (:author book)]
    (.toUpperCase author)))

(defn uppercase-author [book]
  (if-let [author (:author book)]
    (.toUpperCase author)
    "ANONYMOUS"))

(defn uppercase-author [book]
  (when-let [author (:author book)]
    (.toUpperCase author)))
