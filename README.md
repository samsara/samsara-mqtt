# samsara-mqtt

A Clojure MQTT broker

## Usage

```
[samsara/samsara-mqtt "0.1.0"]

(require my.ns
  [samsara-mqtt.mqtt-server :as mqtt]
  [com.stuartsierra.component :as component]
  [reloaded.repl :refer
      [set-init! system init start stop go reset]])

;; Add MQTT component to your system

(defn my-system
  [config]
  (component/system-map
   :mqtt-server (mqtt/new-mqtt-server config)))

;; Function to handle MQTT publish messages.

(defn handler [request]
  (prn request))


;; Initialise and go..
(set-init! #(test-mqtt-system {:enabled true
                               :port 10010
                               :handler-fn handler}))
(go)

```

## License

Copyright © 2015-2016 Samsara's authors.

Distributed under the Apache License v 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
