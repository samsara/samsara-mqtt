(ns samsara-mqtt.service.ping
  (:require [samsara-mqtt.domain.ping :refer [mqtt-pingresp->bytes]]))

(defn pingreq
  "Handles PINGREQ"
  [data]
  (mqtt-pingresp->bytes true))
