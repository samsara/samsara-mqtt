(defproject samsara-mqtt "0.2.0-SNAPSHOT"
  :description "MQTT library for Samsara"
  :url "http://samsara-analytics.io/"

  :scm {:name "github"
        :url "https://github.com/samsara/samsara-mqtt"}

  :license {:name "Apache License 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.6.3"]
                 [aleph "0.4.1"]
                 [gloss "0.2.5"]
                 [org.clojure/tools.logging "0.3.1"]
                 [prismatic/schema "1.1.3"]]

  :profiles {:dev {:dependencies [[midje "1.6.3"]
                                  [clojurewerkz/machine_head "1.0.0-beta9"]
                                  [clj-mqtt "0.4.1-alpha"]]}})
