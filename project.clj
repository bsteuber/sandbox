(defproject sandbox "1.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.3.0-alpha3"]
                 [org.clojure.contrib/standalone "1.3.0-alpha3"]]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]
                     [com.stuartsierra/lazytest "1.2.3"]
                     [lein-lazytest "1.0.1"]]
  :lazytest-path ["src" "test"]
  :repositories {"stuartsierra-releases" "http://stuartsierra.com/maven2"})
