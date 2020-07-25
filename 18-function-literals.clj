;; A function literal gives you a minimalistic alternative
;; to using the fn keyword

;; adventure? recast as a function literal
#(when (= (:genre %1) :adventure) %1)

;; there are no named arguments in function literals
;; they use placeholders e.g. %1 for arguments

;; double a number
#(* 2 %1)

;; add three numbers together
#(+ %1 %2 %3)
