(ns libs.gui.helper
  (:use (libs translate))
  (:import (java.awt Dimension)
           (java.awt.event ActionListener)))

(defn set-tooltip [widget tooltip]
  (.setToolTipText widget (translate tooltip)))

(defn add-action-listener [widget handler]
  (.addActionListener widget
                      (reify ActionListener
                             (actionPerformed [this evt]
                                              (handler (bean evt))))))