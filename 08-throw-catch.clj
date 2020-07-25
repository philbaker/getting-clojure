(try
  (publish-book book)
  (catch ArithmaticException e (println "Math problem."))
  (catch StackOverflowError e (println "Unable to publish..")))

(defn publish-book [book]
  (when (not (:title book))
    (throw
      (ex-info "A book needs a title!" {:book book}))))
