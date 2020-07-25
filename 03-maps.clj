(require 'clojure.java.jbdc)
(def db {:dbtype "derby" :dbname "books"})
(clojure.java.jbdc/query db ["select * from books"])

(def db {:dbtype "MySQL"
         :dbname "books"
         :user "russ"
         :password "noneofyourbeeswax"})
