(ns web.vaadin.utils)

(defn add-component [parent component]
  (.addComponent parent component))

(defn remove-component [parent component]
  (.removeComponent parent component))

(defn replace-component [parent old-component new-component]
  (.replaceComponent parent old-component new-component))

(defn add-window [parent window]
  (.addWindow parent window))

(defn remove-window [parent window]
  (.removeWindow parent window))

(defn add-listener [widget listener]
  (.addListener widget listener))

(defn click-listener [f]
  (reify com.vaadin.ui.Button$ClickListener
         (buttonClick [this evt]
                      (f (bean evt)))))

(defn add-click-listener [widget f]
  (add-listener widget (click-listener f)))

