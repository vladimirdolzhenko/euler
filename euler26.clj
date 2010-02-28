; Project Euler, Problem #26
; http://projecteuler.net/index.php?section=problems&id=26
;
; A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
; 
; 1/2	= 	0.5
; 1/3	= 	0.(3)
; 1/4	= 	0.25
; 1/5	= 	0.2
; 1/6	= 	0.1(6)
; 1/7	= 	0.(142857)
; 1/8	= 	0.125
; 1/9	= 	0.(1)
; 1/10	= 	0.1
; 
; Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
; 
; Find the value of d  1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
;
; Answer: 983 

(ns euler26)

(defn 
	#^{:test (fn []
		(assert (= -1 (index-of #{5} [1 2 3 4])))
		(assert (= 1 (index-of #{2} [1 2 3 4])))
		(assert (= 2 (index-of #{2} [1 1 2])))
	)}
	index-of [pred coll]
	(loop [idx 0 c coll]
		(cond 
		(zero? (count c)) -1  
		(pred (first c)) idx
		:else (recur (inc idx) (rest c))
		)
	)
)	  

(defn 
	#^{:test (fn []
		(assert (= [1 2 5] (column-div (/ 1 8))))
		(assert (= [0 0 7 8 1 2 5] (column-div (/ 1 128))))
		(assert (= [{:cycle [3]}] (column-div (/ 1 3))))
		(assert (= [1 {:cycle [6]}] (column-div (/ 1 6))))
		(assert (= [{:cycle [1 4 2 8 5 7]}] (column-div (/ 1 7))))
		(assert (= [0 {:cycle [7 1 4 2 8 5]}] (column-div (/ 1 14))))
	)}	
	column-div 	
	([r] (do (println r) (column-div r [] [])))
	([r v ns] 
		(if (not (ratio? r)) v
			(let [n (.numerator r) d (.denominator r) 
					p (quot (* r 10) 1)
					q (- (* n 10) (* p d))
					idx (index-of #{r} ns)]
				(if (>= idx 0)
					(conj (subvec v 0 idx) {:cycle (subvec v idx (count v))})	
					(recur (/ q d) (conj v p) (conj ns r)) 
				)
			)	
		)	
	) 
)

(println (test #'index-of))
(println (test #'column-div))

(defn 
	#^{:test (fn []
		(assert (= 7 (run 10)))
	)}	
  run [limit]
  (:d
  (last
  (sort-by #(count (:cycle (last (:r %))))
  (map (fn[d]{:d d :r (column-div (/ 1 d))}) (range 1 limit))
  )
  )
  )
)

(println (run 10))
(println (test #'run))
;(println (run 1000))
