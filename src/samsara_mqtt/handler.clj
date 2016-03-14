(ns samsara-mqtt.handler)

;;MQTT Message handler.
;;The plan is to not support too much of the protocol, so
;;I will put in all handling code in one file.

(defmulti mqtt-handler (fn [message-type _ _]
                         message-type))

(defmethod mqtt-handler :default [_ _ _]
  nil)
