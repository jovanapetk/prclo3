(ns primer3.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [primer3.view.view :as view]
            [primer3.db.dbbroker :as datab]
            [noir.session :as sesh]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
            [noir.util.middleware :as noir-middleware]
            [noir.response :as resp]
            [clojure.string :as s]
            [clj-time.core :as t]))

(def filmaatomID (atom {:id 0}))

(def glumacatomID (atom {:id 0}))

(defn glumac-delete [id]
  (try
    (datab/glumacdelete id)
    (resp/redirect "/glumci")
    (catch Exception e
    (println "Restricted.")
    (resp/redirect "/glumci"))))

(defn glumac-update [params]
  (try
    (datab/glumacedit (get params :imeprezime)  (get params :godinarodjenja)  (get params :pol) (get (first (datab/vratiiddrzave  (get params :nazivdrzave))) :drzavaid) (get @glumacatomID :id))
    (resp/redirect "/glumci")
    (catch Exception e
      (println "edit exception")
      (view/glumac-form-edit (datab/vratiglumcazaizmenu (get @glumacatomID :id) ) (datab/drzavenazivi) (datab/polovi)))))

(defn glumac-new [params]
  (try
    (datab/glumacnew  (get params :imeprezime) (get params :godinarodjenja) (get params :pol) (get (first (datab/vratiiddrzave  (get params :nazivdrzave))) :drzavaid))
    (resp/redirect "/glumac")
    (catch Exception e
      (do (println "exception")
          (resp/redirect "/glumac")))))


(defn glumacfilm-handleizbaci [filmid id ]
  (println @filmaatomID)
  (try
    (datab/glumacfilmremove filmid id)
    (resp/redirect "/glumcifilm")
    (catch Exception e
      (resp/redirect "/glumcifilm"))))

(defn glumacfilm-handledodaj [filmid id]
  (println @filmaatomID)
  (try
    (datab/glumacfilmnew filmid id)
    (resp/redirect "/glumcifilm")
    (catch Exception e
      (resp/redirect "/glumcifilm"))))

(defn pretraga-handle [imePrezime]
  (try
    (datab/glumci imePrezime)
    (view/filmglumci (datab/glumci imePrezime))
    (catch Exception e
      (view/filmglumci (datab/glumci "")))))

(defn film-delete [id]
  (try
    (datab/deletefilm id)
    (resp/redirect "/filmovi")
    (catch Exception e
      (println "Restricted")
      (resp/redirect "/filmovi"))))

(defn film-update [params]
  (try
    (datab/izmenifilm (get params :imefilma)  (get params :kratakopis)  (get params :godinasnimanja) (get (first (datab/vratiiddrzave  (get params :nazivdrzave))) :drzavaid) (sesh/get :username) (get @filmaatomID :id))
    (resp/redirect "/glumcifilm")
    (catch Exception e
      (println "edit exception")
      (view/film-form-edit (datab/vratifilmzaizmenu (get params :filmid)) (datab/drzavenazivi)))))

(defn film-new [params]
  (try
      (datab/filmnew  (get params :imefilma)  (get params :kratakopis)  (get params :godinasnimanja) (get (first (datab/vratiiddrzave  (get params :nazivdrzave))) :drzavaid) (sesh/get :username))
      (swap! filmaatomID assoc :id (get (datab/vratiidfilma (get params :imefilma)) :filmid))
      (resp/redirect "/glumcifilm")
      (catch Exception e
        (do (println "exception")
         (resp/redirect "/film")))))

(defn postavi-ulogovanog [username]
  (sesh/put! :username username)
  (print (sesh/get :username)))

(defn logout-handler []
  (sesh/clear!)
  (swap! filmaatomID assoc :id 0)
  (swap! glumacatomID assoc :id 0)
  (resp/redirect "/"))

(defn login-handler [username password]
  (let [korisnik (datab/login username password)]
    (if (empty? korisnik)
      (view/login-form "Pogresno korisnicko ime/lozinka.")
      (do
        (postavi-ulogovanog username)
        (resp/redirect "/filmovi")))))

(defn register-handler [username password imePrezime]
  (if (empty? (datab/nadjiusername username))
    (if (and (= (count username) 5) (= (count password) 5))
      (do (try
            (datab/signin username password imePrezime)
            (resp/redirect "/")
            (catch Exception e
              (view/register-form "Sva polja moraju biti popunjena."))))
      (view/register-form "Korisnicko ime i lozinka moraju da imaju 5 karaktera/Sva polja moraju biti popunjena."))
    (view/register-form "Izaberite drugo korisnicko ime.")))

(defroutes app-routes
           (GET "/" [] (view/login-form ""))
           (POST "/" [username password] (login-handler username password))
           (GET "/register" [] (view/register-form ""))
           (POST "/register" [username password imePrezime] (register-handler username password imePrezime))
           (GET "/logout" [] (logout-handler))
           (GET "/filmovi" [] (view/filmovi (datab/filmovi)))
           (GET "/filmovi/:id/delete" [id] (film-delete id))
           (GET "/filmovi/:id/edit" [id]
             (do (swap! filmaatomID assoc :id id)
                 (println "atomfilm" @filmaatomID)
                 (view/film-form-edit (datab/vratifilmzaizmenu id) (datab/drzavenazivi))))
           (POST "/filmovi/update" [& params] (film-update params))
           (GET "/film" [] (view/film-form (datab/drzavenazivi)))
           (POST "/film/save" [& params] (film-new params))
           (GET "/glumcifilm" [imePrezime] (pretraga-handle imePrezime))
           (GET "/film/glumci/:id/dodaj" [id] (glumacfilm-handledodaj (get @filmaatomID :id) id))
           (GET "/film/glumci/:id/izbaci" [id] (glumacfilm-handleizbaci (get @filmaatomID :id) id))
           ;;glumac crud
           (GET "/glumci" [] (view/glumci (datab/glumci "")))
           (GET "/glumci/:id/delete" [id] (glumac-delete id))
           (GET "/glumci/:id/edit" [id]
             (do (swap! glumacatomID assoc :id id)
                 (println "atomglumac" @glumacatomID)
                 (view/glumac-form-edit (datab/vratiglumcazaizmenu id) (datab/drzavenazivi) (datab/polovi))))
           (POST "/glumci/update" [& params] (glumac-update params))
           (GET "/glumac" [] (view/glumac-form (datab/drzavenazivi) (datab/polovi)))
           (POST "/glumac/save" [& params] (glumac-new params))
           (route/resources "/")
           (route/not-found "Not Found"))


           ;(def app
           ;  (sesh/wrap-noir-session
           ;    (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)))
           ;  )
           (def app
             (noir-middleware/app-handler
               [app-routes]
               :ring-defaults (assoc-in site-defaults [:security :anti-forgery] false))
             )
           ;

           ;(def app (sesh/wrap-noir-session*
           ; (noir-middleware/app-handler [app-routes]
           ;  :ring-defaults (assoc-in site-defaults [:security :anti-forgery] false))))


