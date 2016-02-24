(ns samsara-mqtt.service.publish
  (:require [samsara-mqtt.domain.publish :refer [bytes->mqtt-publish]]
            [schema.core :as s]
            [reloaded.repl :refer [system]]))


(def publish-schema
  "Schema for MQTT PUBLISH message."
  {:message-type (s/eq :publish)
   :qos          (s/eq 0)
   :topic        (s/eq "topic/events")
   s/Any s/Any})


(defn parse-request
  "Parse and validate byte-array to mqtt-publish.
   Returns a map containing :request and :error"
  [req-bytes]
  (let [req (bytes->mqtt-publish req-bytes)
        err (s/check publish-schema req)]
    {:request req :error err}))


(defn publish
  "Handles MQTT Publish. We only handle QOS-0,
   so there is no response required. Just return nil."
  [req-bytes]
  (let [{:keys [request error]} (parse-request req-bytes)]
    (when error
      (throw (ex-info "Invalid publish message received:" error)))
    
    ;;Pass the request to the handler fn.
    ((-> system :mqtt-server :handler-fn) request))

  ;;Return nil to satisfy the MQTT handler.
  nil)
