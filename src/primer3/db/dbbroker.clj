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

(defn login [username, password]
  (first (login-user {:username username, :password password})))

(defn filmovi []
  (into []  (vrati-filmove)))

(defn deletefilm [filmID]
  (obrisi-film! {:filmID filmID}))

(defn filmnew [imeFilma, kratakOpis, godinaSnimanja, drzavaID, username]
  (film-new! {:imeFilma imeFilma, :kratakOpis kratakOpis, :godinaSnimanja godinaSnimanja, :drzavaID drzavaID, :username username }))

(defn drzavenazivipomocna []
  (into []  (vrati-nazive-drzava)))

(defn drzavenazivi []
  (for [item (drzavenazivipomocna)] (get item :nazivdrzave)))

(defn vratiiddrzave [nazivDrzave]
  (vrati-id-drzave {:nazivDrzave nazivDrzave}))

(defn vratiidfilma [imeFilma]
  (first (vrati-id-filma {:imeFilma imeFilma})))

(defn vratifilmzaizmenu [id]
  (first (vrati-film-premaid {:filmID id})))

(defn izmenifilm [imeFilma, kratakOpis, godinaSnimanja, drzavaID, username, filmID]
  (film-edit! {:imeFilma imeFilma, :kratakOpis kratakOpis, :godinaSnimanja godinaSnimanja, :drzavaID drzavaID, :username username , :filmID filmID}))

(defn glumci [imePrezime]
  (if (or (= imePrezime "")  (empty? imePrezime))
    (vrati-glumce)
    (into []  (pretraga-glumaca {:imePrezime imePrezime}))))

(defn glumacfilmnew [filmID glumacID]
  (glumacfilm-new! {:filmID filmID :glumacID glumacID}))

(defn glumacfilmremove [filmID glumacID]
  (glumacfilm-remove! {:filmID filmID :glumacID glumacID}))

(defn glumacnew [imePrezime, godinaRodjenja, pol, drzavaID]
  (glumac-new! {:imePrezime imePrezime, :godinaRodjenja godinaRodjenja, :pol pol, :drzavaID drzavaID}))

(defn glumacdelete [glumacID]
  (glumac-delete! {:glumacID glumacID}))

(defn vratiglumcazaizmenu [id]
  (first (vrati-glumca-premaid {:glumacID id})))

(defn glumacedit [imePrezime, godinaRodjenja, pol, drzavaID, glumacID]
  (glumac-edit! {:imePrezime imePrezime, :godinaRodjenja godinaRodjenja, :pol pol, :drzavaID drzavaID, :glumacID glumacID}))

(defn polovi []
  (for [item (into []  (vrati-polove))] (get item :pol)))

