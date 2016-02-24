(defproject samsara-mqtt "1.0-SNAPSHOT"
  :description "MQTT library for Samsara"
  :url "http://samsara-analytics.io/"

  :scm {:name "github"
        :url "https://github.com/samsara/samsara-mqtt"}

  :license {:name "Apache License 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [cheshire "5.5.0"]
                 [aleph "0.4.0"]
                 [gloss "0.2.5"]
                 [com.taoensso/timbre "3.4.0"]
                 [prismatic/schema "0.4.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [com.stuartsierra/component "0.3.0"]
                 [reloaded.repl "0.2.1"]]

  :profiles {:dev {:aot :all
                   :dependencies [[midje "1.6.3"]
                                  [clojurewerkz/machine_head "1.0.0-beta9"]
                                  [clj-mqtt "0.4.1-alpha"]]}})
