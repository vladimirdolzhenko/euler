(ns common)

(def fibs (lazy-cat [1 1]
	(map + fibs (rest fibs))))

(def ordinals-and-fibs (map vector (iterate inc 1) fibs))

(defn digits [n] (map #(- (int %) (int \0)) (take-nth 1 (str n))))

(defn sum-of-digits [n]	(apply + (digits n)))

(defn factorial [n]	(apply * (range 1 (inc n))))

(defn c [n k] (/ (factorial n) (* (factorial k) (factorial (- n k)))))

(defn pow [n k]	(apply * (repeat k n)))

(defn squared[n] (pow n 2))
