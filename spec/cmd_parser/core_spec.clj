(ns cmd_parser.core-spec
  (:use [speclj.core] [cmd_parser.core]))

(describe "decoding structured message"
	
	(it "should return a list with an id and a message when passed a string"
		(should= '(5, "cool")(decode-structured-message "5, cool;")))

	(it	"should return 'The number needs to be within range of 0 - 255.' if the number is out of range."
		(should-throw Exception "The number needs to be within range of 0 - 255." (decode-structured-message "478, cool;")))

	(it "should throw an exception when passed something that is NAN as the command id."
		(should-throw Exception "The command id needs to be a number." (decode-structured-message "a, super cool time;")))
)

(describe "decoding multiple structured messages"

	(it "should return a list with two element lists that contain an id and a message when it receives a string"
		(should= '((5, "cool")(7, "sweet")(12, "what")(14, "OMG")) (decode-structured-messages "5, cool; 7, sweet; 12, what; 14, OMG;")))

	(it	"should return 'The number needs to be within range of 0 - 255.' if the number is out of range."
		(should-throw Exception (decode-structured-messages "478, cool;")))

	(it "should throw an exception when passed something that is NAN as the command id."
		(should-throw Exception (decode-structured-messages "a, super cool time;")))
)

(describe "creating a single structured message"

	(it "should be able to create a single structured message in a list when passed an in range command_id and a message"
		(should= "5, cool awesome sweet;" (create-structured-message 5, "cool awesome sweet")))

	(it "should throw an exception when passed a number out of the range of 0 - 255."
		(should-throw Exception "The number needs to be within range of 0 - 255." (create-structured-message 387, "cool")))
		
	(it "should throw an exception when passed something that is NAN as the command id."
		(should-throw Exception "The command id needs to be a number." (create-structured-message "a", "cool")))
		
	(it "should throw an exception if the message is not a string."
		(should-throw Exception "The message needs to be a string." (create-structured-message 12, 16)))
)

(describe "creating multiple structured messages at once"

	(it	"should be able to create a one element list with a structured message when passed list containing one list"
		(should= "5, cool;" (create-structured-messages '((5, "cool")))))
		
	(it "should throw an exception when passed a number out of the range of 0 - 255."
		(should-throw Exception  (create-structured-messages '((387, "cool")))))

	(it "should throw an exception when passed something that is NAN as the command id."
		(should-throw Exception (create-structured-messages '(("a", "cool")))))
		
	(it "should throw an exception if the message is not a string."
		(should-throw Exception (create-structured-messages '((12, 16)))))

	(it "should be able to create a list of multiple structured messages when passed a list of two element lists that are formatted properly"
		(should= "5, cool; 7, sweet; 12, what?; 14, OMG;" (create-structured-messages '((5, "cool")(7, "sweet")(12, "what?")(14, "OMG")))))
)


