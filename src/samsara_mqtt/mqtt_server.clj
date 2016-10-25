(ns samsara-mqtt.mqtt-server
  (:require [clojure.tools.logging :as log]
            [aleph.tcp :as tcp]
            [samsara-mqtt.tcp :refer [tcp-handler]]))


(defn default-callback
  "Default callback. Just prints outs a warning."
  [request]
  (log/warn "MQTT: No handler fn supplied." request))


(def DEFAULT-CONFIG
  {:enabled false
   :callback default-callback})


(defn start
  "Start the MQTT server"
  [port callback]
  (log/info "Samsra MQTT listener started on port:" port)
  (->> {:port port}
       (tcp/start-server (tcp-handler callback))))


(defn stop
  "Stop MQTT server"
  [server]
  (.close server))
