(ns web.vaadin.server
  (:import (com.vaadin.terminal.gwt.server AbstractApplicationServlet)
           (com.vaadin Application)
           (org.mortbay.jetty Server)
           (org.mortbay.jetty.handler AbstractHandler)
           (org.mortbay.jetty.servlet Context
                                      ServletHolder)))

(defn make-app [f]
  (proxy [com.vaadin.Application] []
    (init [] (f this))))

(defn make-servlet [f]
  (let [app (make-app f)]
    (proxy [com.vaadin.terminal.gwt.server.AbstractApplicationServlet] []
      (getApplicationClass [] (class app))
      (getNewApplication [req] app))))

(defonce current-server nil)

(defn stop-server []
  (when current-server
    (.stop current-server)))

(defn make-server [port f]
  (let [server (Server. port)
        context (Context. server "/" Context/SESSIONS)]
    (.addServlet context (ServletHolder. (make-servlet f)) "/*")
    server))

(defn start-server [port f]
  (stop-server)
  (def current-server (make-server port f))
  (.start current-server))

