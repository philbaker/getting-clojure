(defn dispatch-book-format [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standard-map
    (contains? book :book) :alternative-map))

(defmulti normalize-book dispatch-book-format)

(defmethod normalize-book :vector-book [book]
  {:title (first book) :author (second book)})

(defmethod normalize-book :standard-map [book] book)

(defmethod normalize-book :alternative-map [book]
  {:title (:book book) :author (:by book)})

;; Returns the same (standard) book map
(normalize-book {:title "War and Peace" :author "Tolstoy"})

;; Returns {:title "Emma" :author "Austen"}
(normalize-book {:book "Emma" :by "Austen"})

;; returns {:title "1984" :author "Orwell"}
(normalize-book ["1984" "Orwell"])

(defn dispatch-published [book]
  (cond
    (< (:published book) 1928) :public-domain
    (< (:published book) 1978) :old-copyright
    :else :new-copyright))

(defmulti compute-royalties dispatch-published)

(defmethod compute-royalties :public-domain [book] 0)

(defmethod compute-royalties :old-copyright [book]
  ;; Compute royalties based on old copyright law.
  )

(defmethod compute-royalties :new-copyright [book]
  ;; Compute royalties based on new copyright law.
  )


(def books [{:title "Pride and Prejudice" :author "Austen" :genre :remance}
            {:title "World War Z" :author "Brooks" :genre :zombie}])

;; You can use keys like :genre like functions on maps
(defmulti book-description :genre)

(defmethod book-description :romance [book]
  (str "The heart warming new romance by " (:author book)))

(defmethod book-description :zombie [book]
  (str "The heart consuming new zombie adventure by " (:author book)))

(def ppz {:title "Pride and Prejudice and Zombies"
          :author "Grahame-Smith"
          :genre :zombie-romance})

(defmethod book-description :zombie-romance [book]
  (str "The heart warming and consuming new romance by "
(:author book)))
