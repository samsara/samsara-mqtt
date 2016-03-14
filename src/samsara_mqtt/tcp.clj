(ns samsara-mqtt.tcp
  (:require [manifold.stream :as s]
            [manifold.deferred :as d]
            [aleph.tcp :as tcp]
            [samsara-mqtt.handler :refer [mqtt-handler]]
            [samsara-mqtt.service.connect :as connect]
            [samsara-mqtt.service.ping :as ping]
            [samsara-mqtt.service.publish :as publish]
            [samsara-mqtt.domain.util :refer [get-cp-type get-mqtt-message-type to-byte-array]]))

;;;;;;;;; TCP Server

(defn do-safely
  "Calls a function safely by wrapping it within a try block.
   Returns nil when an exception is thrown."
  [f]
  (try (f) (catch Throwable t (do (.printStackTrace t) nil))))


(defn handle
  "Handle data from TCP"
  [data callback]
  (-> data
      first
      get-cp-type
      get-mqtt-message-type
      (mqtt-handler data callback)))

(defn tcp-handler
  "TCP Handler for MQTT"
  [callback]
  (fn [s _]
    (d/loop []
      (d/chain
       ;; Take a message
       (s/take! s ::drained)
       ;; Call the actual mqtt-handler
       (fn [data]
         (if (= ::drained data)
           ::drained
           (do-safely
            #(handle data callback))))
       ;; Write the result
       (fn [result]
         (when-not (= ::drained result)
           (when result
             (s/put! s result))
           (d/recur)))))))
