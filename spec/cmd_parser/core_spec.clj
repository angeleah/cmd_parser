(ns cmd_parser.core-spec
  (:use [speclj.core] [cmd_parser.core]))
(comment
(describe "decoding structured message"

	(it "should return a list with an id and a message when passed a string"
		(should= '(5, "cool") (decode-structured-message "5, cool;")))

	(it	"should return 'The number needs to be within range of 0 - 255.' if the number is out of range."
		(should-throw Exception "The number needs to be within range of 0 - 255." (decode-structured-message "478, cool;")))

	(it "should throw an exception when passed something that is NAN as the command id."
		(should-throw Exception "The command id needs to be a number." (decode-structured-message "a, super cool time;")))

	(it "should be able to decode a single message that is base 64 encoded"
		(should= '(8, "hello") (decode-structured-message "8, aGVsbG8=;")))
)

(describe "decoding multiple structured messages"

	(it "should return a list with two element lists that contain an id and a message when it receives a string"
		(should= '((5, "cool")(7, "sweet")(12, "what")(14, "OMG")) (decode-structured-messages "5, cool; 7, sweet; 12, what; 14, OMG;")))

	(it	"should return 'The number needs to be within range of 0 - 255.' if the number is out of range."
		(should-throw Exception (decode-structured-messages "478, cool;")))

	(it "should throw an exception when passed something that is NAN as the command id."
		(should-throw Exception (decode-structured-messages "a, super cool time;")))

	(it "should return a list with two element lists that contain an id and a message when it receives a string that has base 64 encoded messages"
		(should= '((5, "cool")(7, "sweet")(12, "what")(14, "OMG")) (decode-structured-messages "5, Y29vbA==; 7, c3dlZXQ=; 12, d2hhdA==; 14, T01H;")))
)
)
(describe "creating a single structured message"

	(it "should be able to create a single structured message in a list when passed an in range command_id and a message"
		(should= "5, cool awesome sweet;" (create-structured-message 5, "cool awesome sweet", no-encoding)))

	(it "should throw an exception when passed a number out of the range of 0 - 255."
		(should-throw Exception "The number needs to be within range of 0 - 255." (create-structured-message 387, "cool", no-encoding)))
		
	(it "should throw an exception when passed something that is NAN as the command id."
		(should-throw Exception "The command id needs to be a number." (create-structured-message "a", "cool", no-encoding)))
		
	(it "should throw an exception if the message is not a string."
		(should-throw Exception "The message needs to be a string." (create-structured-message 12, 16, no-encoding)))
		
	(it "should be able to create a base 64 encoded message"
		(should= "5, aGVsbCB5ZWFoIHRoaXMgaXMgYmFzZSA2NCBlbmNvZGVk;" (create-structured-message 5, "hell yeah this is base 64 encoded", base-64)))
)

(describe "creating multiple structured messages at once"

	(it	"should be able to create a one element list with a structured message when passed list containing one list"
		(should= "5, cool;" (create-structured-messages [[5, "cool", no-encoding]])))

	(it "should throw an exception when passed a number out of the range of 0 - 255."
		(should-throw Exception  (create-structured-messages [[387, "cool", no-encoding]])))

	(it "should throw an exception when passed something that is NAN as the command id."
		(should-throw Exception (create-structured-messages [["a", "cool", no-encoding]])))

	(it "should throw an exception if the message is not a string."
		(should-throw Exception (create-structured-messages [[12, 16, no-encoding]])))

	(it "should be able to create a string of multiple structured messages when passed a list of two element lists that are formatted properly"
		(should= "5, cool; 7, sweet; 12, what?; 14, OMG;" (create-structured-messages [[5, "cool", no-encoding][7, "sweet", no-encoding][12, "what?", no-encoding][14, "OMG",no-encoding]])))

	(it "should be able to create a string of multiple structured messages when passed a list of two element lists that are formatted with mixed encoding"
		(should= "5, Y29vbA==; 7, sweet; 12, d2hhdD8=; 14, OMG;" (create-structured-messages [[5, "cool", base-64][7, "sweet", no-encoding][12, "what?", base-64][14, "OMG",no-encoding]])))
	
)

(describe "create and decode base 64 encoded messages"

	(it "should base 64 encode a message"
		(should= "aGVsbG8=" (base-64 "hello")))

	(it "should decode a message"
		(should= "hello" (decode-base-64 "aGVsbG8=")))

;	(it "should return a message that was not encoded"
;		(should= "haha" (decode-base-64 "haha")))

	( it "should return a message that was passed in"
		(should= "hello" (no-encoding "hello")))
)