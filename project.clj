(defproject bteuber/sandbox "1.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.3.0-alpha4"]
                 [org.clojure.contrib/standalone "1.3.0-alpha4"]
                 [com.vaadin/vaadin "6.4.8"]
                 [com.vaadin/vaadin "6.5.1"]
                 [jetty "6.0.2"]
                 [jetty/jetty-util "6.0.2"]
                 [javax.servlet/servlet-api "2.5"]]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]
                     [com.stuartsierra/lazytest "1.2.3"]
                     [lein-lazytest "1.0.1"]]
  :lazytest-path ["src" "test"]
  :repositories {"stuartsierra-releases" "http://stuartsierra.com/maven2"})
