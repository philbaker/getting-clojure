;; An example of how functions can be used to spin 
;; up a web server
(ns ring-example.core
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello from your web application!"})

(defn -main[]
  (jetty/run-jetty handler {:port 8080}))

(defn log-value
  "Log the message and the value. Returns the value."
  [msg value]
  (println msg value)
  value)

(defn wrap-logging
  "Return a function that logs the response."
  [msg handler]
  (fn [request]
    (log-value msg (handler request))))

(defn wrap-content-type
  "Return a function that sets the response content type."
  [handler content-type]
  (fn [request]
    (assoc-in
      (handler request)
      [:headers "Content-Type"]
      content-type)))

(defn handler [request]
  {:status 200
   :body "Hello from your web application!"})

(def app
  (wrap-logging
    "Final response:"
    (wrap-content-type
      (wrap-logging "Initial response:" handler)
      "text/html")))
