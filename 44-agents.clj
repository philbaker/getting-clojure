;; Agents are another mutable state container like
;; atoms or refs. The difference is that agents have
;; a send method which is asynchronous. 

;; example using atom with notification - problem is that this creates duplicate notifications
(def notify-inventory-change
  (println "Inventory has changed"))

(def by-title (atom {}))

(defn add-book [{title :title :as book}]
  (swap!
    by-title
    (fn [by-title-map]
      (notify-inventory-change :add book)
      (assoc by-title-map title book))))

;; Agents are very useful in the case where you need
;; to send an update that something has changed e.g.
;; a new book has been added to the inventory

(ns inventory)

(def by-title (agent {}))

(defn add-book [{title :title :as book}]
  (send
    by-title
    (fn [by-title-map]
      (assoc by-title-map title book))))

;; example with notification
(ns inventory)

(def by-title (agent {}))

(defn add-book [{title :title :as book}]
  (send
    by-title
    (fn [by-title-map]
      (notify-inventory-change :add book)
      (assoc by-title-map title book))))
