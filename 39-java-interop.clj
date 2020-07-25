(ns read-authors
  (:import java.io.File))

;; multiple
(ns read-authors
  (:import java.io File InputStream))

(def authors (File. "authors.txt"))

;; the java.lang package is already imported in clojure because of how
;; widely used it is in java
String ;; java.lang.String
Boolean ;; java.lang.boolean
