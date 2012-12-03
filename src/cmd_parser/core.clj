(ns cmd_parser.core
	(:use [clojure.string]))

(defn decode-structured-message [structured_message]
	)
	
	
	(let [stripped-message (replace structured_message #";" "") ]
	
;		(let [id (read-string (first(split stripped-message #", "))) message (first (rest (split stripped-message #", ")))]
;			(cond
;				(not(integer? id))(throw (Exception. "The command id needs to be a number."))
;				(or(< id 0) (> id 255))(throw (Exception. "The number needs to be within range of 0 - 255."))
;				(and(>= id 0) (<= id 255)) (vector id message)))))

(defn decode-structured-messages [messages]
	messages)

(defn create-structured-message [command_id, message]
		(cond 
			(string? command_id)(throw (Exception. "The command id needs to be a number."))
			(or(< command_id 0) (> command_id 255))(throw (Exception. "The number needs to be within range of 0 - 255."))
			(integer? message)(throw (Exception. "The message needs to be a string."))
			(and(and (>= command_id 0) (<= command_id 255)) (string? message))(apply str [command_id,","" "message ";"])))

(defn create-structured-messages [info]
	(let [data (first info)]
		(cond
			(string? (first data))(throw (Exception. "The command id needs to be a number."))
			(or(< (first data) 0) (> (first data) 255))(throw (Exception. "The number needs to be within range of 0 - 255."))
			(integer? (first(rest data)))(throw (Exception. "The message needs to be a string."))
			(and (>= (first data) 0) (<= (first data) 255)) (apply str[(first data),", ", (first(rest data)), ";"]))))


	;then call itself or use a loop recur, or just lazy seq but what bout breaking out when exceptions are thrown?

	;	(create_structured_messages (rest(list))
	;	)
	;			(if(and (>= command_id 0) (<= command_id 255))
	;		:find a way to(add result to a list (apply str [command_id,","" "message ";"])(throw (Exception. "The number needs to be within range of 0 - 255."))))))
			;pass the rest of the list to create_structured_messages
	;	)

	;then conj it onto the list similar to prime factors or fibb numbers. 
	;you can do the first and then the rest.
	
	
	;then base 64 encoding?