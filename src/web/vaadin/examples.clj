(ns web.vaadin.examples
  (:use (web.vaadin widgets
                    server)))

(defn run-example [name component]
  (let [main (window :caption (str "Vaadin Example: "
                                   name)
                     [component])
        run (fn [app]
              (.setMainWindow app main))]
    (start-server 9999 run)))