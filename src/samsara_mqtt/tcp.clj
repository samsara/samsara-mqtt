(ns samsara-mqtt.tcp
  (:require [manifold.stream :as s]
            [manifold.deferred :as d]
            [samsara-mqtt.domain.connack :as connack]
            [samsara-mqtt.handler :as mqtt]
            [aleph.tcp :as tcp]))

;;;;;;;;; TCP Server

(defn do-safely
  "Calls a function safely by wrapping it within a try block.
   Returns nil when an exception is thrown."
  [f]
  (try (f) (catch Throwable t (do (.printStackTrace t) nil))))


(defn mqtt-handler
  "MQTT Handler for TCP"
  [s info]
  (d/loop []
    (d/chain
     ;; Take a message
     (s/take! s ::drained)
     ;; Call the actual mqtt-handler
     (fn [data]
       (if (= ::drained data)
         ::drained
         (do-safely #(mqtt/mqtt-handler data))))
     ;; Write the result
     (fn [result]
       (when-not (= ::drained result)
         (when result
           (s/put! s result))
         (d/recur))))))
