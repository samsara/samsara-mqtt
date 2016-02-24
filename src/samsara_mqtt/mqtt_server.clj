(ns samsara-mqtt.mqtt-server
  (:require [taoensso.timbre :as log])
  (:require [com.stuartsierra.component :as component]
            [aleph.tcp :as tcp]
            [samsara-mqtt.tcp :refer [mqtt-handler]]))

(defn default-handler
  "Default request handler. Just prints outs a warning."
  [request]
  (log/warn "MQTT: No handler fn supplied." request))

(def DEFAULT-CONFIG {:enabled false
                     :handler-fn default-handler})

(defrecord MqttServer [port enabled backend server]
  component/Lifecycle

  (start [component]
    (when enabled
      (log/info "Samsra MQTT listener started on port:" port)
      (if server
        component
        (->>
         {:port port}
         (tcp/start-server mqtt-handler)
         (assoc component :server)))))

  (stop [component]
    (if server
      (->> component :server .close (assoc component :server))
      component)))

(defn new-mqtt-server
  [config]
  (map->MqttServer (merge DEFAULT-CONFIG config)))


