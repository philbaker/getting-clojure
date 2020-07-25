(ns inventory)

(def by-title (atom {}))

(def total-copies (atom 0))

(defn add-book [{title :title :as book}]
  (swap! by-title #(assoc % title book))
  ;; There's a problem here - the two atoms are out of sync and there is
  ;; an unavoidable window where the new book is in inventory but not counted
  ;; the way around this problem is to use refs instead
  (swap! total-copies + (:copies book)))


;; using refs
(ns inventory)

(def by-title (ref {}))

(def total-copies (ref 0))

(defn add-book [{title :title :as book}]
  (dosync
    ;; alter is the ref-updating function for refs and uses the same style
    ;; as swap! - the difference is that alter must happen within the body
    ;; of a call to dosync
    (alter by-title #(assoc % title book))
    (alter total-copies + (:copies book))))

(ns inventory)

