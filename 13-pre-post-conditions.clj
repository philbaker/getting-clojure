;; Publish a book using the (unseen) print-book
;; and ship-book functions.
(defn publish-book [book]
  (when-not (contains? book :title)
    (throw (ex-info "Books must contain :title" {:book book})))
  (print-book book)
  (ship-book book))

;; Shorter version using :pre condition
(defn publish-book [book]
  {:pre [(:title book)]}
  (print-book book)
  (ship-book book))

;; Ensure books have authors and titles
(defn publish-book [book]
  {:pre [(:title book) (:author book)]}
  (print-book book)
  (ship-book book))

;; Ensure return value from ship-book is Boolean
(defn pubish-book [book]
  {:pre [(:title book) (:author book)]
   :post [(boolean? %)]}
  (print-book book)
  (ship-book book))
