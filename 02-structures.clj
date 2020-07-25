  ; vectors
  (vector true 3 "four" 5)
  ;; The same as [true 3 "four" 5]
  (vector)
  ;; The same as []

  (def novels ["Emma" "Coma" "War and Peace"])
  (count novels) ; => 3
  (first novels) ; => ("Emma")
  (rest novels) ; => ("Coma" "War and Peace")
  (rest (rest novels)) ; => ("War and Peace")

  (rest ["Ready Player One"]) ; => an empty collection
  (rest []) ; => an empty collection

  (def year-books ["1491" "April 1865", "1984", "2001"])
  (def third-book (first (rest (rest year-books)))) ; => "1984"
  (nth year-books 2) ; => '1984"
  (year-books 2) ; => '1984"

  ; lists
  '(1 2 3)
  '(1 2 3 "four" 5 "six")
  '(1 2.0 2.9999 "four" 5.001 "six")
  '([1 2 ("a" "list" "inside a" "vectior")] "inside" "a" "list")
  (list 1 2 3 "four" 5 "six") ; function to create a list

  (conj novels "Carrie") ; Adds "Carrie" to list of novels (end)
  (cons novels "Carrie") ; Adds "Carrie" to list of novels (start)

  (def poems '("Iliad" "Odyssey" "Now We Are Six"))

  (count poems) ; => 3
  (first poems) ; "Iliad"
  (rest poems) ; ("Odyssey" "Now We Are Six")
  (nth poems 2) ; "Now We Are Six".

  (def poems '("Iliad" "Odyssey" "Now We Are Six"))
  (conj poems "Jabberwocky") ; ("Jabberwocky" "Iliad" "Odyssey" "Now We Are Six")

  (def vector-poems ["Iliad" "Odyssey" "Now We Are Six"])
  (conj vector-poems "Jabberwocky") ; ["Iliad" "Odyssey" "Now We Are Six" "Jabberwocky"]

  (def novels ["Emma" "Coma" "War and Peace"])
  ;; this does nothing - novels is immutable and cannot change but the addition
  ;; of "Jaws" is not given an symbol
  (conj novels "Jaws") 
  ;; this binds the value to 'more-novels'
  (def more-novels (conj novels "Jaws"))

  ;; Create a list
  (def novels '("Emma" "Coma" "War and Peace"))
  (conj novels "Jaws")

  (defn escape-html [string]
    (replace-all string [["&" "&am;"]
                         ["\"" "&quot;"]
                         ["<" "&lt;"]
                         [">" "&gt;"]]))

  ;; maps
  {"title" "Oliver Twist" "author" "Dickens" "published" 1838}
  (hash-map "title" "Oliver Twist"
            "author" "Dickens"
            "published" 1838)

  (def book {"title" "Oliver Twist"
             "author" "Dickens"
             "published" 1838})
  (get book "published") ; => 1838
  (book "published") ; same as above but more common way to get value from map

  ;; keywords - basic data type in clojure. Commonly as the keys for maps
  :title
  :author
  :published
  :word-count
  :preface&introduction
  :chapter-1-and-2

  (def book {:title "Oliver Twist"
             :author "Dickens"
             :published 1838})
  (book :published) ; => 1838
  (:published book) ; same as above but more common (this works with keywords - not strings)

  ;; adding values to a map
  (assoc book :page-count 362)
  ;; removing values from a map
  (dissoc book :title :author :published)

  ;; get all keys in a map
  (keys book)
  ;; get all values in a map
  (vals book)

  ;; sets (values can only be added to a set once)
  (def genres #{:sci-fi :romance :mystery})
  (def authors #{"Dickens" "Austen" "King"})
  (contains? authors "Austen") ; => true
  (contains? genres "Austen") ; => false
  (authors "Austen") ; => "Austen"
  (genres :historical) ; => nil
  (:sci-fi genres) ; => :sci-fi
  (:historical genres) ; => :sci-fi

  ;; A four element set
  (def more-authors (conj authors "Clarke"))
  (conj more-authors "Clarke")
  (disj more-authors "King") ; remove element
  
