(ns cmd_parser.core)

(defn create_structured_message [command_id, message]
	(if(and (>= command_id 0) (<= command_id 255))
	(apply str [command_id,","" "message ";"])"The number needs to be within range of 0 - 255."))

(defn decode_structured_message [structured_message]
	(if(and (>= (re-find #"\d+" structured_message) 0) (<= (re-find #"\d+" structured_message) 255))
	(list (re-find #"\d+" structured_message))))
	;then test message part does not have , or ; if it does not,
	;then conj it onto the list
	
	
	;find a way to create_st_message when passed multiples(()()())
	;and to decode when passed multiples
	
	
	
	;then base 64 encoding?