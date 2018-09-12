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
            ))

(defn postavi-ulogovanog [username password]
    (sesh/put! :username username)
    (sesh/put! :password password))

(defn login-handler [username password]
  (let [korisnik (datab/login username password)]
    (if (empty? korisnik)
    (view/login-form "Pogresno korisnicko ime/lozinka.")
     (do
       (postavi-ulogovanog username password)
       (print (sesh/get :username))
       (resp/redirect "/main")))))

(defn register-handler [username password imePrezime]
  (if (empty? (datab/nadjiusername username))
    (if (and (= (count username) 5)(= (count password) 5) )
    (do (try
          (datab/signin username password imePrezime)
          (resp/redirect "/")
          (catch Exception e
            (view/register-form "Sva polja moraju biti popunjena."))))
    (view/register-form "Korisnicko ime i lozinka moraju da imaju 5 karaktera/Sva polja moraju biti popunjena."))
  (view/register-form "Izaberite drugo korisnicko ime.")))

(defroutes app-routes
  (GET "/" [] (view/login-form "" ))
  (POST "/" [username password] (login-handler username password))
  (GET "/register" [] (view/register-form ""))
  (POST "/register" [username password imePrezime] (register-handler username password imePrezime))
  (GET "/main" [] (view/main-page))
  (route/resources "/")
  (route/not-found "Not Found"))


(def app
 (sesh/wrap-noir-session
   (wrap-defaults  app-routes (assoc-in site-defaults [:security :anti-forgery] false)))
  )
(def app
  (noir-middleware/app-handler
    [app-routes]
    :ring-defaults (assoc-in site-defaults [:security :anti-forgery] false))
  )