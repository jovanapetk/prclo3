(ns primer3.view.view
  (:use [hiccup.element]
        [hiccup.core]
        [hiccup.form]
        [noir.session :as sesh]))

(defn login-base [& body]
  (html[:html
    {:lang "en"}
    [:head
     [:title "Login/Register"]
     [:meta {:charset "UTF-8"}]
     [:meta
      {:content "width=device-width, initial-scale=1", :name "viewport"}]
     [:link
      {:href "images/icons/favicon.ico", :type "image/png", :rel "icon"}]
     [:link
      {:href "vendor/bootstrap/css/bootstrap.min.css",
       :type "text/css",
       :rel "stylesheet"}]
     [:link
      {:href "fonts/font-awesome-4.7.0/css/font-awesome.min.css",
       :type "text/css",
       :rel "stylesheet"}]
     [:link
      {:href "fonts/iconic/css/material-design-iconic-font.min.css",
       :type "text/css",
       :rel "stylesheet"}]
     [:link
      {:href "vendor/animate/animate.css",
       :type "text/css",
       :rel "stylesheet"}]
     [:link
      {:href "vendor/css-hamburgers/hamburgers.min.css",
       :type "text/css",
       :rel "stylesheet"}]
     [:link
      {:href "vendor/animsition/css/animsition.min.css",
       :type "text/css",
       :rel "stylesheet"}]
     [:link
      {:href "vendor/select2/select2.min.css",
       :type "text/css",
       :rel "stylesheet"}]
     [:link
      {:href "vendor/daterangepicker/daterangepicker.css",
       :type "text/css",
       :rel "stylesheet"}]
     [:link {:href "css/util.css", :type "text/css", :rel "stylesheet"}]
     [:link {:href "css/main.css", :type "text/css", :rel "stylesheet"}]]
    [:body
     [:div.limiter
      [:div.container-login100
       {:style "background-image: url('images/bg-01.jpg');"}
       [:div.wrap-login100
        body]]]
     [:div#dropDownSelect1]
     [:script {:src "vendor/jquery/jquery-3.2.1.min.js"}]
     [:script {:src "vendor/animsition/js/animsition.min.js"}]
     [:script {:src "vendor/bootstrap/js/popper.js"}]
     [:script {:src "vendor/bootstrap/js/bootstrap.min.js"}]
     [:script {:src "vendor/select2/select2.min.js"}]
     [:script {:src "vendor/daterangepicker/moment.min.js"}]
     [:script {:src "vendor/daterangepicker/daterangepicker.js"}]
     [:script {:src "vendor/countdowntime/countdowntime.js"}]
     [:script {:src "js/main.js"}]]])
  )

(defn login-form [ alerts & [username password]]
  (login-base
    [:div
     (if (= alerts "")
       (do (println "prazan je"))
       (do [:div.alert.alert-warning
            alerts]))
     (form-to {:class "login100-form.validate-form"}[:post "/"]
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
       (submit-button {:class "login100-form-btn" , :type "submit" , :name "Login" } "Login" )
      ][:div.text-center.p-t-90
        [:a.txt1
         {:href "/register"}
         "\n\t\t\t\t\t\t\tRegistruj se\n\t\t\t\t\t\t"]])
     ]
    )
  )
(defn register-form [ alerts & [username password imePrezime] ]
  (login-base
    [:div
     (if (= alerts "")
       (do (println "prazan je"))
       (do [:div.alert.alert-warning
            alerts]))
     (form-to {:class "login100-form.validate-form"}[:post "/register"]
              [:span.login100-form-logo [:i.zmdi.zmdi-landscape]]
              [:span.login100-form-title.p-b-34.p-t-27
               "\n\t\t\t\t\t\tRegistruj se\n\t\t\t\t\t"]
              [:div.wrap-input100.validate-input
               {:data-validate "Enter username"}
               (text-area {:class "input100", :placeholder "Username", :name "username", :type "text" } username)
               [:span.focus-input100]]
              [:div.wrap-input100.validate-input
               {:data-validate "Enter password"}
               (text-area {:class "input100", :placeholder "Password", :name "password", :type "text"} password)
               [:span.focus-input100 ]]
              [:div.wrap-input100.validate-input
               (text-area {:class "input100", :placeholder "Ime Prezime", :name "imePrezime", :type "text"} imePrezime)
               [:span.focus-input100 ]]
              [:div.contact100-form-checkbox
               [:input#ckb1.input-checkbox100
                {:name "remember-me", :type "checkbox"}]]
              [:div.container-login100-form-btn
               (submit-button {:class "login100-form-btn" , :type "submit" , :name "Sign in" } "Sign in" )
               ])]))
(defn main-page []
  (html[:html [:head [:title "proba"]] [:body [:p (sesh/get!  :username)] ">\n"]]))