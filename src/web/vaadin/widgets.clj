(ns web.vaadin.widgets
  (:use (libs args imperative)
        (libs.java wrap)
        (web.vaadin utils))
  (:import (com.vaadin.ui Window
                          VerticalLayout
                          HorizontalLayout
                          GridLayout
                          FormLayout
                          Panel
                          SplitPanel
                          TabSheet
                          Accordion
                          Label
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
                          Tree)))

(defn window [& args]
  (let [[components args]        (parse-last-arg args)
        [{:keys [windows]} args] (parse-options [:windows] args)
        widget                   (apply construct Window args)]
    (domap #(add-component widget %) components)
    (domap #(add-window widget %) windows)
    widget))

(defn horizontal [& args]
  (let [[components args] (parse-last-arg args)
        widget            (apply construct HorizontalLayout args)]
    (domap #(add-component widget %) components)
    widget))

(defn vertical [& args]
  (let [[components args] (parse-last-arg args)
        widget            (apply construct VerticalLayout args)]
    (domap #(add-component widget %) components)
    widget))

(defn grid [& args]
  (let [[components args] (parse-last-arg args)
        widget            (apply construct GridLayout args)]
    (domap #(add-component widget %) components)
    widget))

(defn form [& args]
  (let [[components args] (parse-last-arg args)
        widget            (apply construct FormLayout args)]
    (domap #(add-component widget %) components)
    widget))

(defn panel [& args]
  (let [[components args] (parse-last-arg args)
        widget            (apply construct Panel args)]
    (domap #(add-component widget %) components)
    widget))

(defn split-panel [& args]
  (let [[components args] (parse-last-arg args)
        widget            (apply construct SplitPanel args)]
    (domap #(add-component widget %) components)
    widget))

(defn tabs [& args]
  (let [[components args] (parse-last-arg args)
        widget            (apply construct TabSheet args)]
    (domap #(add-component widget %) components)
    widget))

(defn accordion [& args]
  (let [[components args] (parse-last-arg args)
        widget            (apply construct Accordion args)]
    (domap #(add-component widget %) components)
    widget))

(defn label [& args]
  (apply construct Label args))

(defn button [& args]
  (let [[{:keys [on-click]} args] (parse-options [:on-click] args)
        widget                    (apply construct Button args)]
    (when on-click
      (add-click-listener widget on-click))
    widget))

(defn check-box [& args]
  (apply construct CheckBox args))

(defn text-field [& args]
  (apply construct TextField args))

(defn rich-text-area [& args]
  (apply construct RichTextArea args))

(defn date-field [& args]
  (apply construct DateField args))

(defn inline-date-field [& args]
  (apply construct InlineDateField args))

(defn form [& args]
  (apply construct Form args))

(defn login-form [& args]
  (apply construct LoginForm args))

(defn popup-date-field [& args]
  (apply construct PopupDateField args))

(defn progress-indicator [& args]
  (apply construct ProgressIndicator args))

(defn slider [& args]
  (apply construct Slider args))

(defn select [& args]
  (apply construct Select args))

(defn combo-box [& args]
  (apply construct ComboBox args))

(defn list-select [& args]
  (apply construct ListSelect args))

(defn native-select [& args]
  (apply construct NativeSelect args))

(defn twin-col-select [& args]
  (apply construct TwinColSelect args))

(defn option-group [& args]
  (apply construct OptionGroup args))

(defn table [& args]
  (apply construct Table args))

(defn tree [& args]
  (apply construct Tree args))


