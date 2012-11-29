(ns cmd_parser.core-spec
  (:use [speclj.core] [cmd_parser.core]))

(describe "decoding structured messages"

	(it "should return a list with an id and a message when it receives a string"
		(should= (5 "cool") (decode_structured_message "5, cool;")))

;	(it	"should return 'i can not parse that' if it is not passed a string."
;		(should= "I can not parse that." parse(72)))
)

(describe "creating a structured messages"

	(it "should be able to create a single structured message in a list when passed an in range command_id and a message"
		(should= "5, cool;" (create_structured_message 5, "cool")))
		
	(it "should return 'The number needs to be within range of 0 - 255.' when passed an out of range command_id"
		(should= "The number needs to be within range of 0 - 255." (create_structured_message 387, "cool")))
		
;	(it "should be able to create multiple structured messages when passed a list of two element lists")
)

