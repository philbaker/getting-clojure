;; Apply metadata to a value
(def books1 (with-meta ["Emma" "1984"] {:favorite-books true}))
(def books1 ^:favorite-books ["Emma" "1984"])
(meta books1) ; {:favorite-books true}

;; Otherwise identical vectors with different metadata
(def books2 (with-meta ["Emma" "1984"] {:favorite-books true}))
(def books3 (with-meta ["Emma" "1984"] {:favorite-books false}))
;; Are still equal
(= books2 books3) ; true

;; Docstrings are stored as metadata
(defn add2
  "Return the sum of two numbers"
  [a b]
  (+ a b))
(meta #'add2) ; {:arglists ([a b]), :doc "Return the sum of two numbers", :line 1, :column 1, :file "NO_SOURCE_PATH", :name add2, :ns #object[clojure.lang.Namespace 0x1120e169 "codetool.core"]}

;; Metadata is just a map so you can do familiar things with the returned data:
(def md (meta books3))
(count md) ; 1
(vals md) ; (false) 
