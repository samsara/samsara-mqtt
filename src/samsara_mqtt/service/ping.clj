(ns samsara-mqtt.service.ping
  (:require [samsara-mqtt.domain.ping :refer [mqtt-pingresp->bytes]]
            [samsara-mqtt.handler :refer [mqtt-handler]]))

(defmethod mqtt-handler :ping [_ data _]
  (mqtt-pingresp->bytes true))
