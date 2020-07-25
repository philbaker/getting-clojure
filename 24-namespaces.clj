;; create and switch to the pricing namespace
(ns pricing)

(def discount-rate 0.15)

(defn discount-price [book]
  (* (- 1.0 discount-rate) (:price book)))

;; ns switches namespace if namespace already exists
(ns user)

;; switch back
(ns pricing)
(println (discount-price {:title "Emma" :price 9.99})) ; 8.4915

;; namespaces are ordinary Clojure values
(println "Current ns" *ns*) ; Current ns #object[clojure.lang.Namespace 0x350b3b4f pricing]
(find-ns 'user) ; #object[clojure.lang.Namespace 0x2675e9a9 "user"]
(ns-map 'user) ; includes all the predefined vars
(namespace 'pricing/discount-price) ; "pricing"

;; show everyting in clojure.core
(ns-map 'clojure.core)
