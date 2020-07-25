;; clojure.spec is a library that enables you to validate the shape of your values

(defn book? [x]
  (and
    (map? x)
    (string? (:author x))
    (string? (:title x))
    (pos-int? (:copies x))))

(book? {:title "Getting Clojure" :author "Olsen" :copies 10000000})

(ns inventory.core
  (:require [clojure.spec.alpha :as s]))

(s/valid? number? 44) ; true
(s/valid? number? :hello) ; false

(def n-gt-10 (s/and number? #(> % 10)))

(s/valid? n-gt-10 1) ; false
(s/valid? n-gt-10 10) ; false
(s/valid? n-gt-10 11) ; true

(def n-gt-10-lt-100
  (s/and number? #(> % 10) #(< % 100)))

(def n-or-s (s/or :a-number number? :a-string string?))

(s/valid? n-or-s "Hello!") ; true
(s/valid? n-or-s 99) ; true
(s/valid? n-or-s nil) ; false
(s/valid? n-or-s 'foo) ; false

(def n-gt-10-or-s (s/or :greater-10 n-gt-10 :a-symbol symbol?))

;; spec'ing collections
(def s-n-s-n (s/cat :s1 string? :n1 number? :s2 string? :n2 number?))
(s/valid? s-n-s-n ["Emma" 1815 "Jaws" 1974]) ; true

(def book-s
  (s/keys :req-un [:inventory.core/title
                   :inventory.core/author
                   :inventory.core/copies]))

(s/valid? book-s {:title "Emma" :author "Austen" :copies 10}) ; true
(s/valid? book-s {:title "Arabian Nights" :copies 17}) ; false
(s/valid? book-s {:title "2001" :author "Clarke" :copies 1 :published 1968}) ; true

;; Registering specs
(s/def
  :inventory.core/book
  (s/keys
    :req-un
    [:inventory.core/title :inventory.core/author
  :inventory.core/copies]))

;; Validate a book against the registered spec
(s/valid? :inventory.core/book {:title "Dracula" :author "Stoker" :copies 10}) ; true

;; Same as book-s above
(s/def ::book (s/keys :req-un [::title ::author ::copies]))

;; Spec'ing maps
(s/def ::title string?)
(s/def ::author string?)
(s/def ::copies int?)
(s/def ::book (s/keys :req-un [::title ::author ::copies]))

(s/valid? ::book {:author :austen :title :emma})
;; explain tells you what went wrong when a spec doesn't match (it prints and always returns nil)
(s/explain ::book {:author "austen" :title "emma"}) ; failed - missing :copies
;; conform tells you all about a successful match (returns result)
(s/conform s-n-s-n ["Emma" 1815 "Jaws" 1974]) ; {:s1 "Emma", :n1 1815, :s2 "Jaws", :n2 1974}
(s/conform number? "hello") ; :clojure.spec.alpha/invalid
(s/conform number? 11) ; 11

;; Register a spec: An inventory is a collection of books
(s/def :inventory.core/inventory
  (s/coll-of ::book))

(defn find-by-title
  [title inventory]
  {:pre [(s/valid? ::title title)
         (s/valid? ::inventory inventory)]}
  (some #(when (= (:title %) title) %) inventory))

;; more concise approach
(defn find-by-title
  [title inventory]
  (some #(when (= (:title %) title) %) inventory))

;; Register spec
(s/fdef find-by-title
  :args (s/cat :title ::title
               :inventory ::inventory))

(require '[clojure.spec.test.alpha :as st])
(st/instrument 'inventory.core/find-by-title)

;; "Emma" - failed: map? at: [:inventory] spec: :inventory.core/book
;; "2001" - failed: map? at: [:inventory] spec: :inventory.core/book
;; "Jaws" - failed: map? at: [:inventory] spec: :inventory.core/book
(find-by-title "Emma" ["Emma" "2001" "Jaws"])

;; Note: spec-based argument checking can slow things down - it's most useful during
;; development / testing

;; Spec-drivent tests
(defn book-blurb [book]
  (str "The best selling book " (:title book) " by " (:author book)))
(s/fdef book-blurb :args (s/cat :book ::book))
(require '[clojure.spec.test.alpha :as stest])
(stest/check 'inventory.core/book-blurb)
(s/fdef book-blurb
  :args (s/cat :book ::book)
  ;; return value must be a string and contan the works "The best selling"
  :ret (s/and string? (partial re-find #"The best selling")))

(defn check-return [{:keys [args ret]}]
  (let [author (-> args :book :author)]
    (not (neg? (.indexOf ret author)))))

(s/fdef book-blurb
  :args (s/cat :book ::book)
  :ret (s/and string? (partial re-find #"The best selling"))
  :fn check-return)
