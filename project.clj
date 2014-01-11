(defproject cmd_parser "0.1.0-SNAPSHOT"
  :description "converts intput into a message"
  :dependencies [[org.clojure/clojure "1.5.1"]
              [org.clojure/data.codec "0.1.0"]]
  :profiles {:dev {:dependencies [[speclj "2.9.1"]]}}
  :plugins [[speclj "2.9.1"]]
  :test-paths ["spec"]
  :main cmd_parser.core
)
