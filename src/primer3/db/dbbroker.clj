(ns primer3.db.dbbroker
(:require [yesql.core :refer [defqueries]]
  [clojure.java.jdbc :as jdbc]))
(def db-spec {
              :subprotocol "mysql"
              :subname "//localhost:3306/clojurepr"
              :user "jovana"
              :password "jovana"
              :zeroDateTimeBehaviour "convertToNull"
              })
(defqueries "dbqueries.sql"
            {:connection db-spec})
(defn signin [username, password, imeprezime ]
  (if (> (count imeprezime)  1)
    (signin-user! { :username username, :password password, :imePrezime imeprezime})
    (throw (Exception. "")))
  )
(defn login [username, password]
  (first (login-user {:username username, :password password})))
(defn nadjiusername [username]
  (proveriusername {:username username}))