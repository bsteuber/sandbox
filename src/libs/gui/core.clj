(ns libs.gui.core)

(defmulti handle (fn [widget event-type handler]
                   [(class widget) event-type]))


(defprotocol Widget
  (get-data     [_])
  (raw-set-data [_ data]))