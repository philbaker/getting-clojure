;; A drawback of maps is that you can put anything in them, so intention
;; is not always clear
(let [watson-1 (get-watson-1)
      watson-2 (get-watson-2)]
      ;; Do something with our watsons...
      )

;; A fictional character
(defn get-watson-1 []
  {:name "John Watson"
   :appears-in "Sign of the Four"
   :author "Doyle"})

;; A computer
(defn get-watson-2 [] {:cpu "Power7" :no-cpus 2880 :storage-gb 4000})

;; records are maps with some predefined keys
(defrecord FictionalCharacter [name appears-in author])
(defrecord SuperComputer [cpu no-cpus storage-gb])

(def watson (->FictionalCharacter "John Watson" "Sign of the Four" "Doyle")) 
; #user.FictionalCharacter{:name John Watson, :appears-in Sign of the Four, :author Doyle}

(def elizabeth (map->FictionalCharacter
                  {:name "Elizabeth Bennet"
                   :appears-in "Pride & Prejudice"
                   :author "Austen"}))
;; #user.FictionalCharacter{:name Elizabeth Bennet, :appears-in Pride & Prejudice, :author Austen}

;; any function that works with a map will also work with a record
(:name elizabeth) ; => "Elizabeth Bennet"
(:appears-in watson) ; => "Sign of the four"

(def specific-watson (assoc watson :appears-in "Sign of the Four"))
;; #user.FictionalCharacter{:name John Watson, :appears-in Sign of the Four, :author Doyle}

(def more-about-watson (assoc watson :address "221B Baker Street"))

(def watson-1 (->FictionalCharacter
                "John Watson"
                "Sign of the Four"
                "Doyle"))

(def watson-2 (->SuperComputer "Power7" 2880 4000))

(class watson-1) ; user.FictionalCharacter
(class watson-2) ; user.SuperComputer

(instance? FictionalCharacter watson-1) ; true
(instance? SuperComputer watson-1) ; false

;; class and instance can be very useful when using the REPL but generally
;; should be avoided in real code
;; e.g. do not do this:
(defn process-thing [x]
  (if (= (instance? FictionalCharacter x))
    (process-fictional-character x)
    (process-computer x)))
