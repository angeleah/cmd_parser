(ns cmd_parser.core
	(:use [clojure.string]))

(defn decode-structured-message [structured_message]
	(let [stripped-message (replace structured_message #";" "") ]
		(let [id (read-string (first(split stripped-message #", "))) message (first (rest (split stripped-message #", ")))]
			(cond
				(not(integer? id))(throw (Exception. "The command id needs to be a number."))
				(or(< id 0) (> id 255))(throw (Exception. "The number needs to be within range of 0 - 255."))
				(and(>= id 0) (<= id 255)) (list id message)))))

(defn decode-structured-messages [input]
	(let [result (map decode-structured-message (split input #";"))]
		(take (count result) result)))

(defn create-structured-message [command_id, message]
		(cond 
			(string? command_id)(throw (Exception. "The command id needs to be a number."))
			(or(< command_id 0) (> command_id 255))(throw (Exception. "The number needs to be within range of 0 - 255."))
			(integer? message)(throw (Exception. "The message needs to be a string."))
			(and(and (>= command_id 0) (<= command_id 255)) (string? message))(apply str [command_id,","" "message ";"])))

(defn create-structured-message-from-pair [element]
	(create-structured-message (first element) (first(rest element))))

(defn create-structured-messages [info]
	 (join " "(map create-structured-message-from-pair info)))
	
	;then base 64 encoding?