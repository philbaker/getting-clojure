(def counter 0)
(defn greeting-message [req]
  (if (zero? (mod counter 100))
  (str "Congrats! You are the " counter " visitor!")
  (str "Welcome to Blotts Books!")))

;; managing state with atoms
(def counter (atom 0))

(defn greeting-message [req]
  (swap! counter inc)
  (if (= @counter 500)
    (str "Congrats! You are the " @counter " visitor!")
    (str "Welcome to Blotts Books!")))

;; swapping maps
(ns inventory)

(def by-title (atom {}))

(defn add-book [{title :title :as book}]
  (swap! by-title #(assoc % title book)))

(defn del-book [title]
  (swap! by-title #(dissoc % title)))

(defn find-book [title]
  (get @by-title title))

(find-book "Emma") ; nil

(add-book {:title "1984", :copies 1948})
(add-book {:title "Emma", :copies 100})

(find-book "Emma") ; {:title "Emma", :copies 100}
(find-book "1984") ; {:title "1984", :copies 1948}
