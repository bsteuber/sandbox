(ns web.vaadin.listeners)

(defn click-listener [f]
  (reify com.vaadin.ui.Button$ClickListener
         (buttonClick [this evt]
                      (f (bean evt)))))

(defn add-listener [widget listener]
  (.addListener widget listener))

(defn add-click-listener [widget f]
  (add-listener widget (click-listener f)))

