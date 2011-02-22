(ns web.vaadin.examples.hello
  (:use (web.vaadin examples
                    widgets)))

(def hello
  (label :caption "Hello World"))

(run-example "Hello" hello)

