(ns scratch
  (:require [cheshire.core :refer :all]
            [samsara-mqtt.mqtt-server :as mqtt]
            [com.stuartsierra.component :as component]
            [reloaded.repl :refer
             [set-init! system init start stop go reset]]
            [clojurewerkz.machine-head.client :as mh]))


(defn test-mqtt-system
  [config]
  (component/system-map
   :mqtt-server (mqtt/new-mqtt-server config)))


(set-init! #(test-mqtt-system {:enabled true :port 10010}))

(go)

(def event {:sourceId "5340-dfd0350"
            :eventName "session.created"
            :timestamp "2015-09-20T19:31:36+00:00"
            :user      "svittal@gmail.com"})

(defn publish
  [event]
  (let [id   (mh/generate-id)
        conn (mh/connect "tcp://localhost:10010" id)]
    (mh/publish conn "topic/events" event 0)))


(generate-string event)

(publish (generate-string event))



