(ns web.vaadin.widgets
  (:use (libs.java wrap)
        (web.vaadin server)))

(defn apply-options [obj options]
  (update-slots obj (partition 2 options))
  obj)

(defmacro wrap-widget [class]
  (let [full-name (symbol (str "com.vaadin.ui." class))]
    `(do (wrap ~full-name)
         (defn ~(symbol (clojure-name (str class))) [& options#]
           (apply-options (~(symbol (str full-name ".")))
                          options#)))))

(defmacro wrap-container [class]
  (let [full-name (symbol (str "com.vaadin.ui." class))]
    `(do (wrap ~full-name)
         (defn ~(symbol (clojure-name (str class))) [& args#]
           (let [[options# components#] (if (even? (count args#))
                                          [args# nil]
                                          [(butlast args#) (last args#)])
                 container# (apply-options
                             (~(symbol (str full-name ".")))
                             options#)]
             (doseq [c# components#]
               (.addComponent container# c#))
             container#)))))

(defmacro wrap-widgets [& widgets]
  `(do ~@(for [w widgets]
           `(wrap-widget ~w))))

(defmacro wrap-containers [& containers]
  `(do ~@(for [c containers]
           `(wrap-container ~c))))

(wrap-containers Window
                 VerticalLayout
                 HorizontalLayout
                 GridLayout
                 FormLayout
                 Panel
                 SplitPanel
                 TabSheet
                 Accordion)

(wrap-widgets Label
              Button
              CheckBox
              TextField
              RichTextArea
              DateField
              InlineDateField
              Form
              LoginForm
              PopupDateField
              ProgressIndicator
              Slider
              Select
              ComboBox
              ListSelect
              NativeSelect
              TwinColSelect
              OptionGroup
              Table
              Tree)