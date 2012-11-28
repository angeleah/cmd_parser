(ns cmd_parser.core-spec
  (:use [speclj.core] [cmd_parser.core]))

;(describe "decoding input sent into the parser"

;	(it	"should return 'i can not parse that' if it is not passed a string."
;		(should= "I can not parse that." parse(72)))

;	(it "should be able to parse the id 5 from the message."
;		(should= [5] parse("5, hello;")))
;)

(describe "creating a structured message"

	(it "should be able to create a structured message when passed a command_id and a message"
		(should= "5, cool;" (create_structured_message 5, "cool")))
)

