; Project Euler, Problem #67
; http://projecteuler.net/index.php?section=problems&id=67
;
; By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

;    3
;   7 4
;  2 4 6
; 8 5 9 3
; 
; That is, 3 + 7 + 4 + 9 = 23.
;
; Find the maximum total from top to bottom in http://projecteuler.net/project/triangle.txt, a 15K text file containing a triangle with one-hundred rows.
; 
; NOTE: This is a much more difficult version of Problem 18. It is not possible to try every route to solve this problem, as there are 2^99 altogether! If you could check one trillion (10^12) routes every second it would take over twenty billion years to check them all. There is an efficient algorithm to solve it. ;o)
;
; Answer: 7273

(ns euler67)
(use '[common])

(import '[java.io BufferedReader FileReader ])

(def table	
	(with-open [reader (BufferedReader. (FileReader. "triangle.txt"))]
		(vec (doall (map (fn[line]
				(map #(Integer/parseInt %) (vec (.split line " ")))
			) 
			(line-seq reader)))
		)
	)
)

(defn simple[col y]
	(map (fn[[x value]]
		(+
			value	
			(let [next-row (nth col (inc y))]	
				(max
					(nth next-row x)
					(nth next-row (inc x))
				)	
			)	
		))	
		(map vector (iterate inc 0) (nth col y))
	)	
)

(defn simplify[col]
	(if (>= (count col) 2)
		(recur (conj
			(subvec col 0 (- (count col) 2))
			(simple col (- (count col) 2))
		))
		(first (first col))
	)		
)

(time (println (simplify table)))
;(println table)
;(println (nth (nth table 2) 2))
