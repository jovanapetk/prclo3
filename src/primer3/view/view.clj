(ns primer3.view.view
  (:use [hiccup.element]
        [hiccup.core]
        [hiccup.form]
        [hiccup.page]
        [primer3.view.layout :as layout]
        [noir.validation :as vali]
        ))

(defn login-form [alerts & [username password]]
  (layout/login-base
    [:div
     (if (= alerts "")
       (do (println "prazan je"))
       (do [:div.alert.alert-warning
            alerts]))
     (form-to {:class "login100-form.validate-form"} [:post "/"]
              [:span.login100-form-logo [:i.zmdi.zmdi-landscape]]
              [:span.login100-form-title.p-b-34.p-t-27
               "\n\t\t\t\t\t\tLog in\n\t\t\t\t\t"]
              [:div.wrap-input100.validate-input
               {:data-validate "Enter username"}
               (text-area {:class "input100", :placeholder "Username", :name "username", :type "text"} username)
               [:span.focus-input100 {:data-placeholder ""}]]
              [:div.wrap-input100.validate-input
               {:data-validate "Enter password"}
               (text-area {:class "input100", :placeholder "Password", :name "password", :type "text"} password)
               [:span.focus-input100 {:data-placeholder ""}]]
              [:div.contact100-form-checkbox
               [:input#ckb1.input-checkbox100
                {:name "remember-me", :type "checkbox"}]]
              [:div.container-login100-form-btn
               (submit-button {:class "login100-form-btn", :type "submit", :name "Login"} "Login")
               ] [:div.text-center.p-t-90
                  [:a.txt1
                   {:href "/register"}
                   "\n\t\t\t\t\t\t\tRegistruj se\n\t\t\t\t\t\t"]])
     ]
    )
  )
(defn register-form [alerts & [username password imePrezime]]
  (layout/login-base
    [:div
     (if (= alerts "")
       (do (println "prazan je"))
       (do [:div.alert.alert-warning
            alerts]))
     (form-to {:class "login100-form.validate-form"} [:post "/register"]
              [:span.login100-form-logo [:i.zmdi.zmdi-landscape]]
              [:span.login100-form-title.p-b-34.p-t-27
               "\n\t\t\t\t\t\tRegistruj se\n\t\t\t\t\t"]
              [:div.wrap-input100.validate-input
               {:data-validate "Enter username"}
               (text-area {:class "input100", :placeholder "Username", :name "username", :type "text"} username)
               [:span.focus-input100]]
              [:div.wrap-input100.validate-input
               {:data-validate "Enter password"}
               (text-area {:class "input100", :placeholder "Password", :name "password", :type "text"} password)
               [:span.focus-input100]]
              [:div.wrap-input100.validate-input
               (text-area {:class "input100", :placeholder "Ime Prezime", :name "imePrezime", :type "text"} imePrezime)
               [:span.focus-input100]]
              [:div.contact100-form-checkbox
               [:input#ckb1.input-checkbox100
                {:name "remember-me", :type "checkbox"}]]
              [:div.container-login100-form-btn
               (submit-button {:class "login100-form-btn", :type "submit", :name "Sign in"} "Sign in")
               ])]))

(defn filmovi [listafilmova]
  (layout/main-base
    [:div.table100
     [:table
      [:thead
       [:tr.table100-head
        [:th.column1 "Ime filma"]
        [:th.column2 "Godina snimanja"]
        [:th.column3 "Država"]
        [:th.column4 "username"]
        [:th.column5]
        [:th.column6]]]
      [:tbody
       (for [item (map
                    (fn [film]
                      [:tr
                       [:td.column1 (:imefilma film)]
                       [:td.column2 (:godinasnimanja film)]
                       [:td.column3 (:nazivdrzave film)]
                       [:td.column4 (:username film)]
                       [:td.column5 [:a {:class "btn btn-dark" :id "edit" :href (str "/filmovi/" (:filmid film) "/edit")} "Edit"] " "]
                       [:td.column6 [:a {:class "btn btn-dark" :id "delete" :href (str "/filmovi/" (:filmid film) "/delete")} "Delete"] " "]])
                    listafilmova)] item)]]]))

(defn film-form [listadrzava]
  (layout/form-base
    [:div
     (hiccup.form/form-to {:class "contact100-form.validate-form"} [:post "/film/save"]
                          [:span.contact100-form-title "\n\t\t\t\t\tFilm\n\t\t\t\t"]
                          [:div.wrap-input100.validate-input
                           (text-area {:class "input100", :id "imefilma" :placeholder "Naziv filma",  :type "text"} "imefilma")
                           [:span.focus-input100]]
                          [:div.wrap-input100.validate-input
                           (text-area {:class "input100", :id "godinasnimanja", :placeholder "Godina snimanja", :type "text"} "godinasnimanja" )
                           [:span.focus-input100]]
                          [:div.wrap-input100.input100-select
                           [:span.label-input100 "Zemlja"]
                           [:div
                            (drop-down {:class "selection-2"} "nazivdrzave" listadrzava )]
                           [:span.focus-input100]]
                          [:div.wrap-input100.validate-input
                           (text-field {:class "input100", :id "kratakopis", :placeholder "Kratak opis"} "kratakopis" )
                           [:span.focus-input100]]
                          [:div.container-contact100-form-btn
                           (submit-button {:class "btn btn-dark" :type "submit"} "Izaberi postavu")])]))

(defn film-form-edit [film listadrzava]
  (layout/form-base
    [:div
     (hiccup.form/form-to {:class "contact100-form.validate-form"} [:post "/filmovi/update"]
                          [:span.contact100-form-title "\n\t\t\t\t\tFilm " (:filmid film) "\n\t\t\t\t"]
                          [:div.wrap-input100.validate-input
                           (text-area {:class "input100", :id "imefilma" :placeholder "Naziv filma", :name "imefilma", :type "text"} "imefilma" (:imefilma film))
                           [:span.focus-input100]]
                          [:div.wrap-input100.validate-input
                           (text-area {:class "input100", :id "godinasnimanja", :placeholder "Godina snimanja", :name "godinasnimanja", :type "text"} "godinasnimanja" (:godinasnimanja film))
                           [:span.focus-input100]]
                          [:div.wrap-input100.input100-select
                           [:span.label-input100 "Zemlja"]
                           [:div
                            (drop-down {:class "selection-2"} "nazivdrzave" listadrzava (:nazivdrzave film))]
                           [:span.focus-input100]]
                          [:div.wrap-input100.validate-input
                           (text-field {:class "input100", :id "kratakopis", :placeholder "Kratak opis", :name "kratakopis"} "kratakopis" (:kratakopis film))
                           [:span.focus-input100]]
                          [:div.container-contact100-form-btn
                           (submit-button {:class "btn btn-dark" :type "submit"} "Izmeni postavu")])]))



(defn filmglumci [listaglumaca & imePrezime]
  (layout/main-base
    [:div.container
     [:div.row
      [:div.col-12
       [:div#custom-search-input
        (hiccup.form/form-to {:class "contact100-form.validate-form"} [:get "/glumcifilm"]
                             [:div.input-group
                              (text-area {:class "search-query form-control", :placeholder "Ime Prezime", :type "text", :name "imePrezime"} imePrezime)
                              [:span.input-group-btn
                               (submit-button {:class "btn btn-dark" :type "submit"} "Pretraži")
                               [:span.fa.fa-search]]])
        ]]]
     [:div.col-12 [:h2 " ."]]]
    [:div.table100

     [:table
      [:thead
       [:tr.table100-head
        [:th.column1 "Ime i prezime"]
        [:th.column2 "Godina rodjenja"]
        [:th.column3 "Drzava rodjenja"]
        [:th.column4 "Pol"]
        [:th.column5]
        [:th.column6]
        ]]
      [:tbody
       (for [item (map
                    (fn [glumac]
                      [:tr
                       [:td.column1 (:imeprezime glumac)]
                       [:td.column2 (:godinarodjenja glumac)]
                       [:td.column3 (:nazivdrzave glumac)]
                       [:td.column4 (:pol glumac)]
                       [:td.column5 [:a {:class "btn btn-dark" :id "dodaj" :href (str "/film/glumci/" (:glumacid glumac) "/dodaj")} "Dodaj"] " "]
                       [:td.column6 [:a {:class "btn btn-dark" :id "izbaci" :href (str "/film/glumci/" (:glumacid glumac) "/izbaci")} "Izbaci"] " "]])
                    listaglumaca)] item)]]]))


(defn glumci [listaglumaca]
  (layout/main-base
    [:div.table100
     [:table
      [:thead
       [:tr.table100-head
        [:th.column1 "Ime Prezime"]
        [:th.column2 "Godina rodjenja"]
        [:th.column3 "Drzava porekla"]
        [:th.column4 "Pol"]
        [:th.column5]
        [:th.column6]]]
      [:tbody
       (for [item (map
                    (fn [glumac]
                      [:tr
                       [:td.column1 (:imeprezime glumac)]
                       [:td.column2 (:godinarodjenja glumac)]
                       [:td.column3 (:nazivdrzave glumac)]
                       [:td.column4 (:pol glumac)]
                       [:td.column5 [:a {:class "btn btn-dark" :id "edit" :href (str "/glumci/" (:glumacid glumac) "/edit")} "Edit"] " "]
                       [:td.column6 [:a {:class "btn btn-dark" :id "delete" :href (str "/glumci/" (:glumacid glumac) "/delete")} "Delete"] " "]])
                    listaglumaca)] item)]]]))

(defn glumac-form [listadrzava polovi]
  (layout/form-base
    [:div
     (hiccup.form/form-to {:class "contact100-form.validate-form"} [:post "/glumac/save"]
                          [:span.contact100-form-title "\n\t\t\t\t\tGlumac\n\t\t\t\t"]
                          [:div.wrap-input100.validate-input
                           (text-area {:class "input100", :id "imeprezime" :placeholder "Ime Prezime",  :type "text"} "imeprezime")
                           [:span.focus-input100]]
                          [:div.wrap-input100.validate-input
                           (text-area {:class "input100", :id "godinarodjenja", :placeholder "Godina rodjenja ", :type "text"} "godinarodjenja" )
                           [:span.focus-input100]]
                          [:div.wrap-input100.input100-select
                           [:span.label-input100 "Zemlja"]
                           [:div
                            (drop-down {:class "selection-2"} "nazivdrzave" listadrzava )]
                           [:span.focus-input100]]
                          [:span.label-input100 "Pol"]
                          [:div
                           (drop-down {:class "selection-2"} "pol"  polovi)]
                          [:span.focus-input100]
                          [:div.container-contact100-form-btn
                           (submit-button {:class "btn btn-dark" :type "submit"} "Sačuvaj")])]))

(defn glumac-form-edit [glumac listadrzava polovi]
  (layout/form-base
    [:div
     (hiccup.form/form-to {:class "contact100-form.validate-form"} [:post "/glumci/update"]
                          [:span.contact100-form-title "\n\t\t\t\t\tGlumac\n\t\t\t\t"]
                          [:div.wrap-input100.validate-input
                           (text-area {:class "input100", :id "imeprezime" :placeholder "Ime Prezime",  :type "text"} "imeprezime" (:imeprezime glumac))
                           [:span.focus-input100]]
                          [:div.wrap-input100.validate-input
                           (text-area {:class "input100", :id "godinarodjenja", :placeholder "Godina rodjenja ", :type "text"} "godinarodjenja" (:godinarodjenja glumac))
                           [:span.focus-input100]]
                          [:div.wrap-input100.input100-select
                           [:span.label-input100 "Zemlja"]
                           [:div
                            (drop-down {:class "selection-2"} "nazivdrzave" listadrzava (:nazivdrzave glumac))]
                           [:span.focus-input100]]
                          [:span.label-input100 "Pol"]
                          [:div
                           (drop-down {:class "selection-2"} "pol"  polovi (:pol glumac))]
                          [:span.focus-input100]
                          [:div.container-contact100-form-btn
                           (submit-button {:class "btn btn-dark" :type "submit"} "Sačuvaj")])]))

;;ppp