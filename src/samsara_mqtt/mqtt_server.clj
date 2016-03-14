(ns samsara-mqtt.mqtt-server
  (:require [taoensso.timbre :as log])
  (:require [com.stuartsierra.component :as component]
            [aleph.tcp :as tcp]
            [samsara-mqtt.tcp :refer [tcp-handler]]))

(defn default-callback
  "Default callback. Just prints outs a warning."
  [request]
  (log/warn "MQTT: No handler fn supplied." request))


(def DEFAULT-CONFIG {:enabled false
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


(defrecord MqttServer [port enabled backend server callback]
  component/Lifecycle

  (start [component]
    (when enabled
      (if server
        component
        (->> (start port callback)
         (assoc component :server)))))

  (stop [component]
    (if server
      (->> component :server stop (assoc component :server))
      component)))

(defn new-mqtt-server
  [config]
  (map->MqttServer (merge DEFAULT-CONFIG config)))
