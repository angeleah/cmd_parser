(ns cmd_parser.core
	(:use
		[clojure.string :as str :only [split join]])
	(:require
		[clojure.data.codec.base64 :as b64 ]
		[clojure.java.io :as io]))

(defn base-64 [message]
	 (String. (b64/encode (.getBytes message))))
	;http://clojure-log.n01se.net/date/2012-10-09.html

(defn no-encoding [message]
	message)

(defn plain-text [message]
	message)

(defn decode-base-64 [message]
	 (String. (b64/decode (.getBytes message))))

(defn decode-structured-message
	([structured_message] (decode-structured-message structured_message plain-text))
	([structured_message decoder]
	(let [stripped-message (str/replace structured_message #";" "") ]
		(let [id (read-string (first(split stripped-message #", "))) message (first (rest (split stripped-message #", ")))]
			(cond
				(not(integer? id))(throw (Exception. "The command id needs to be a number."))
				(or (< id 0)(> id 255)) (throw (Exception. "The number needs to be within range of 0 - 255."))
				(and(>= id 0) (<= id 255))(list id (decoder message)))))))

(defn decode-structured-messages
	([input] (decode-structured-message input plain-text))
	([input decoder]
	(let [result (map decode-structured-message (split input #";"))]
		(doall result))))

		(defn decode-structured-messages [input decoder]
			(let [result (map (fn [structured-message] (decode-structured-message structured-message decoder )) (split input #";"))]
				(doall result)))

(defn create-structured-message [command_id message encoding]
(prn command_id message encoding)
		(cond 
			(string? command_id)(throw (Exception. "The command id needs to be a number."))
			(or(< command_id 0) (> command_id 255))(throw (Exception. "The number needs to be within range of 0 - 255."))
			(integer? message)(throw (Exception. "The message needs to be a string."))
			(and(and (>= command_id 0) (<= command_id 255)) (string? message))(apply str [command_id,", " (encoding message) ";"])))

(defn create-structured-message-from-list [element]
	(create-structured-message (first element) (first(rest element)) (last element)))

(defn create-structured-messages [info]
	 (join " "(map create-structured-message-from-list info)))