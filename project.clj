(defproject cmd_parser "1.0.0-SNAPSHOT"
  :description "converts intput into a message"
  :dependencies [[org.clojure/clojure "1.3.0"]
				 [org.clojure/data.codec "0.1.0"]]
  :dev-dependencies [[speclj "2.1.1"]]
  :test-path "spec/"
  :main cmd_parser.core
)