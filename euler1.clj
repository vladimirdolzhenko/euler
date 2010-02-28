; Project Euler, Problem #1
; http://projecteuler.net/index.php?section=problems&id=1
;
; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
; 
; Find the sum of all the multiples of 3 or 5 below 1000.
;
; Answer: 233168

(ns euler1)

(defn 
#^{:test (fn []
	(assert (true? (divisible-by-3-or-5? 3)))
	(assert (true? (divisible-by-3-or-5? 5)))
	(assert (true? (divisible-by-3-or-5? 15)))
	(assert (false? (divisible-by-3-or-5? 4)))
	(assert (false? (divisible-by-3-or-5? 11)))
)}
divisible-by-3-or-5? [num] (or (zero? (mod num 3)) (zero? (mod num 5)))) 

(defn 
#^{:test (fn []
	(assert (= 23 (run 10)))
)}
run[n] (reduce + (filter divisible-by-3-or-5? (range n)))
)

(println (test #'divisible-by-3-or-5?))
(println (test #'run))
(println (run 1000))
