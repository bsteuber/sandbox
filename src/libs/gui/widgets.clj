(ns libs.gui.widgets
  (:use (libs gui)))

(remove-all-methods handle)

(defmethod handle [AbstractButton :click]
  [btn _ handler]
  (add-action-listener btn handler))

(defmethod handle [JTextField :enter]
  [btn _ handler]
  (add-action-listener btn handler))

(defmethod handle [JTextComponent :change]
  [btn _ handler]
  (.. btn
      getDocument
      (addDocumentListener
       (reify DocumentListener
              (changedUpdate [_ evt]
                             (handler {:type :changed
                                       }))
              (insertUpdate [_ evt]
                            (handler (bean evt)))
              (removeUpdate [_ evt]
                            (handler {:type :remove
                                      }))))))

(defn button [& {:keys [text
                        handle]}])


(button :text "Foo"
        :handle {:click (fn ..)
                 ::mouse-exit })

(def t (javax.swing.JTextField. "hallo"))

(handle t :change println)

(doto (javax.swing.JFrame.)
                (.add t)
                .pack
                .show)


