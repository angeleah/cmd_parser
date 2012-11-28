(ns cmd_parser.core)

;(defn parse [input] 
;	cond())
	
(defn create_structured_message [command_id, message]
	(apply str [command_id,","" "message ";"]))
	
	
; to take in multiple messages, get a count, then do for each? on count? 