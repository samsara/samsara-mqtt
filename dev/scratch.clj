(ns scratch
  (:require [cheshire.core :refer :all]
            [samsara-mqtt.mqtt-server :as mqtt]
            [clojurewerkz.machine-head.client :as mh]
            [clojure.pprint :refer [pprint]]))


(comment

  (def server (mqtt/start 10010 pprint))

  (def event {:sourceId  "5340-dfd0350"
              :eventName "session.created"
              :timestamp (System/currentTimeMillis)
              :user      "testuser123"})

  (defn publish
    [event]
    (let [id   (mh/generate-id)
          conn (mh/connect "tcp://localhost:10010" id)]
      (mh/publish conn "topic/events" event 0)
      (mh/disconnect conn)))

  (publish (generate-string event))

  )
