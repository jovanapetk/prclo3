(defproject primer3 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [hiccup "1.0.5"]
                 [yesql "0.5.3"]
                 [mysql/mysql-connector-java "5.1.32"]
                 [lib-noir "0.9.9"]
                 [ring/ring-anti-forgery "1.1.0"]
                 [bouncer "1.0.1"]
                 [clj-time "0.14.4"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler primer3.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
