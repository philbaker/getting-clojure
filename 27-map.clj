(def some-numbers [1, 53, 811])
(def doubled (map #(* 2 %) some-numbers)) ; (2 106 1622)

(map (fn [book] (:title book)) books) ; ("Deep Six" "Dracula" "Emma" "2001")
(map :title books) ; same as above

(map (fn [book] (count (:title book))) books) ; (8 7 4 4)
(map (comp count :title) books) ; same as above
(for [b books]
  (count (:title b))) ; same again
