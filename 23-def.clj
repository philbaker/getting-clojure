;; def binds a symbol to a value
;; it is useful for long lasting, stable name-to-value bindings
(def title "Emma")
;; commonly used for constants
(def PI 3.14)
(def ISBN-LENGTH 13)
(def OLD-ISBN-LENGTH 10)
(def COMPANY-NAME "Blotts Books")

;; defn is a combination of def and fn
(defn book-description [book]
  (str (:title book)
       " Written by "
       (:author book)))

;; the above is a cleaner way to write this:
(def book-description
  (fn [book]
    (str (:title book)
         " Written by "
         (:author book))))

;; once defined you can use the bindings that come out of def in other defs
(def isbn-lengths [OLD-ISBN-LENGTH ISBN-LENGTH])

;; and inside of functions
(defn valid-isbn [isbn]
  (or (= (count isbn) OLD-ISBN-LENGTH)
      (= (count isbn) ISBN-LENGTH)))

;; this def involves two values: the string "Austen" and the symbol author
(def author "Austen")
;; symbols have a lot in common with keywords. The difference is that keywords
;; always stand for themselves (:title will always get :title back) but symbols
;; are typically bound to another value. So if you evaluate the symbol author
;; you will get back the value to which it is bound; "Austen"
'author ; The symbol author, not the string "Austen"
'title ; A symbol that starts with a 't'

(def author "Austen") ; Make a var
#'author ; Get at the var for author -> "Austen"
(def the-var #'author) ; Grab the var
(.get the-var) ; Get the value of the var: "Austen"
(.-sym the var) ; Get the symbol of the var: author

;; def and defn are mutable in clojure. This is so that repl-based programming
;; is easier to do. Mutability is allowed in production clojure but is discouraged

(def debug-enabled false)

(defn debug [msg]
  (if debug-enabled
    (println msg)))

;; the binding expression temporarily sets vars with a supplied value
;; debug-enabled set to true only while following statements are evaluated
(binding [debug-enabled true] 
  (debug "Calling the function")
  (some-troublesome-function-that-needs-logging)
  (debug "Back from that function"))

;; any var that we use in 'binding' needs to be declared as dynamic:
;; Make debug-enabled a dynamic var
;; clojure convention is that dynamic vars should begin and end with *
(def ^:dynamic *debug-enabled* false)

(defn debug [msg]
  (if *debug-enabled*
    (println msg)))

(binding [*debug-enabled* true]
  (debug "Calling that function")
  (some-troublesome-function-that-needs-logging)
  (debug "Back from that function"))
