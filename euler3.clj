; Project Euler, Problem #3
; http://projecteuler.net/index.php?section=problems&id=3
;
; The prime factors of 13195 are 5, 7, 13 and 29.
;
; What is the largest prime factor of the number 600851475143 ?
;
; Answer: 6857

(ns euler3)

(defn 
 #^{:test (fn []
	(assert (= 29 (max-factor 13195)))
  )}	 
	max-factor
	([n] (max-factor n (int (Math/sqrt n))))
	([n limit]
		((fn [n cur]
			(if (= n cur) n
				(if (zero? (mod n cur)) (recur (/ n cur) cur)
					(recur n (inc cur)))
			)
		) n 2)
	)
)

(println (test #'max-factor))
(println (time (max-factor 600851475143)))
