;; Clojure rule: if you can turn it into a sequence, you can destructure it

(def artists [:monet :austen :beethoven :dickinson])

(let [painter (first artists)
      novelist (second artists)]
  (println "The painter is:" painter
           "and the novelist is" novelist)) ; The painter is: :monet and the novelist is :austen

(let [[painter novelist] artists]
  (println "The painter is:" painter
           "and the novelist is:" novelist)) ; same as above

;; The painter is :monet
;; The novelist is :austen
;; The composer is :beethoven
;; The poet is :dickinson
(let [[painter novelist composer poet] artists]
  (println "The painter is" painter)
  (println "The novelist is" novelist)
  (println "The composer is" composer)
  (println "The poet is" poet))

;; Use dummy because we don't care about the first two values
(let [[dummy dummy composer poet] artists]
  (println "The composer is" composer)
  (println "The poet is" poet))

;; Same as above but using the closure convention for ignored values (_)
(let [[_ _ composer poet] artists]
  (println "The composer is" composer)
  (println "The poet is" poet))

;; vector
(def pairs [[:monet :austen] [:beethoven :dickinson]])

;; The painter is :monet
;; The composer is :beethoven
(let [[[painter] [composer]] pairs]
  (println "The painter is" painter)
  (println "The composer is" composer))

;; The painter is :monet
;; The composer is :dickinson
(let [[[painter] [_ composer]] pairs]
  (println "The painter is" painter)
  (println "The composer is" composer))

;; if we switch names from a vector to a list the process of destructuring
;; stays the same
(def artist-list '(:monet :austen :beethoven :dickinson))

;; The painter is :monet
;; The novelist is :austen
;; The composer is :beethoven
(let [[painter novelist composer] artist-list]
  (println "The painter is" painter)
  (println "The novelist is" novelist)
  (println "The composer is" composer))

;; How do you spell Jane?
;; J
;; a
;; n
;; e
(let [[c1 c2 c3 c4] "Jane"]
  (println "How do you spell Jane?")
  (println c1)
  (println c2)
  (println c3)
  (println c4))

(defn artist-description [[novelist poet]]
  (str "The novelist is " novelist " and the poet is " poet))

;; "The novelist is :austen and the poet is :dickinson"
(artist-description [:austen :dickinson])

(defn artist-description [shout [novelist poet]]
  (let [msg (str "Novelist is " novelist
                 "and the poet is " poet)]
    (if shout (.toUpperCase msg) msg)))

(def artist-map {:painter :monet :novelist :austen})

;; The painter is :monet
;; The novelist is :austen
(let [{painter :painter writer :novelist} artist-map]
  (println "The painter is" painter)
  (println "The novelist is" writer))

(def austen {:name "Jane Austen"
             :parents {:father "George" :mother "Cassandra"}
             :dates {:born 1775 :died 1817}})

;; map destructuring
(let [{{dad :father mom :mother} :parents} austen]
  (println "Jane Austen's dad's name was" dad)
  (println "Jane Austen's mom's name was" mom))

(let [{name :name
       {mom :mother} :parents
       {dob :born} :dates} austen]
  (println name "was born in" dob)
  (println name "mother's name was" mom))

(def author {:name "Jane Austen"
             :books [{:title "Sense and Sensibility" :published 1811}
                     {:title "Emma" :published 1815}]})

(let [{name :name [_ book] :books} author]
  (println "The author is" name)
  (println "One of the author's books is" book))

(def authors [{:name "Jane Austen" :born 1775}
              {:name "Charles Dickens" :born 1812}])

(let [[{dob-1 :born} {dob-2 :born}] authors]
  (println "One author was born in" dob-1)
  (println "The other author was born in" dob-2))

{:name "Romeo" :age 16 :gender :male}

(defn character-desc [{name :name age :age gender :gender}]
  (str "Name: " name " age: " age " gender: " gender))

;; same as above
(defn character-desc [{:keys [name age gender]}]
  (str "Name: " name " age: " age " gender: " gender))

(defn character-desc [{:keys [name gender] age-in-years :age}]
  (str "Name: " name " age: " age-in-years " gender: " gender))

(defn add-greeting [character]
  (let [{:keys [name age]} character]
    (assoc character
           :greeting
           (str "Hello, my name is " name " and I am " age "."))))

(defn add-greeting [{:keys [name age] :as character}]
  (assoc character
         :greeting
         (str "Hello, my name is " name " and I am " age "."
