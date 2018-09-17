(ns primer3.view.layout
  (:use [hiccup.element]
        [hiccup.core]
        [hiccup.form]
        [hiccup.page]
        [noir.session :as sesh]))
(defn login-base [& body]
  (html5[:html
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
         [:script {:src "js/main.js"}]]]))
(defn form-base [& body]
  (html5[:html
         {:lang "en"}
         [:head
          [:title "Forma"]
          [:meta {:charset "UTF-8"}]
          [:meta
           {:content "width=device-width, initial-scale=1", :name "viewport"}]
          [:link
           {:href "forma/images/icons/favicon.ico", :type "image/png", :rel "icon"}]
          [:link
           {:href "vendor/bootstrap/css/bootstrap.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "forma/fonts/font-awesome-4.7.0/css/font-awesome.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "forma/vendor/animate/animate.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "forma/vendor/css-hamburgers/hamburgers.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "forma/vendor/animsition/css/animsition.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "forma/vendor/select2/select2.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "forma/vendor/daterangepicker/daterangepicker.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link {:href "forma/css/util.css", :type "text/css", :rel "stylesheet"}]
          [:link {:href "forma/css/main.css", :type "text/css", :rel "stylesheet"}]]
         [:body
          [:nav.navbar.navbar-expand-lg.navbar-dark.bg-dark
           [:a.navbar-brand {:href "/logout"} "Dobrodošli: " (sesh/get :username) " Kliknite za logout"]
           [:button.navbar-toggler
            {:aria-label "Toggle navigation",
             :aria-expanded "false",
             :aria-controls "navbarNavDropdown",
             :data-target "#navbarNavDropdown",
             :data-toggle "collapse",
             :type "button"}
            [:span.navbar-toggler-icon]]
           [:div#navbarNavDropdown.collapse.navbar-collapse
            [:ul.navbar-nav
             [:li.nav-item.dropdown
              [:a#navbarDropdownMenuLink.nav-link.dropdown-toggle
               {:aria-expanded "false",
                :aria-haspopup "true",
                :data-toggle "dropdown",
                :href "#"}
               "\n         Filmovi\n        "]
              [:div.dropdown-menu
               {:aria-labelledby "navbarDropdownMenuLink"}
               [:a.dropdown-item {:href "/film"} "Unesi novog"]
               [:a.dropdown-item {:href "/filmovi"} "Izmeni/Izbrisi"]]]
             [:li.nav-item.dropdown
              [:a#navbarDropdownMenuLink.nav-link.dropdown-toggle
               {:aria-expanded "false",
                :aria-haspopup "true",
                :data-toggle "dropdown",
                :href "#"}
               "\n          Glumci\n        "]
              [:div.dropdown-menu
               {:aria-labelledby "navbarDropdownMenuLink"}
               [:a.dropdown-item {:href "/glumac"} "Unesi novog"]
               [:a.dropdown-item {:href "/glumci"} "Izmeni/Izbrisi"]]]]]]
          [:div.container-contact100
           [:div.wrap-contact100
            body]]
          [:div#dropDownSelect1]
          [:script {:src "forma/vendor/jquery/jquery-3.2.1.min.js"}]
          [:script {:src "forma/vendor/animsition/js/animsition.min.js"}]
          [:script {:src "forma/vendor/bootstrap/js/popper.js"}]
          [:script {:src "forma/vendor/bootstrap/js/bootstrap.min.js"}]
          [:script {:src "forma/vendor/select2/select2.min.js"}]
          [:script
           "\n\t\t$(\".selection-2\").select2({\n\t\t\tminimumResultsForSearch: 20,\n\t\t\tdropdownParent: $('#dropDownSelect1')\n\t\t});\n\t"]
          [:script {:src "forma/vendor/daterangepicker/moment.min.js"}]
          [:script {:src "forma/vendor/daterangepicker/daterangepicker.js"}]
          [:script {:src "forma/vendor/countdowntime/countdowntime.js"}]
          [:script {:src "forma/js/main.js"}]]]))

(defn main-base [& body]
  (html5[:html
         {:lang "en"}
         [:head
          [:title "Filmovi"]
          [:meta {:charset "UTF-8"}]
          [:meta
           {:content "width=device-width, initial-scale=1", :name "viewport"}]
          "\t\n\t"
          [:link
           {:href "mimages/icons/favicon.ico", :type "image/png", :rel "icon"}]
          [:link
           {:href "mvendor/bootstrap/css/bootstrap.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "mfonts/font-awesome-4.7.0/css/font-awesome.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "mvendor/animate/animate.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "mvendor/css-hamburgers/hamburgers.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "mvendor/select2/select2.min.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link
           {:href "mvendor/perfect-scrollbar/perfect-scrollbar.css",
            :type "text/css",
            :rel "stylesheet"}]
          [:link {:href "mcss/util.css", :type "text/css", :rel "stylesheet"}]
          [:link {:href "mcss/main.css", :type "text/css", :rel "stylesheet"}]]
         [:body
          [:nav.navbar.navbar-expand-lg.navbar-dark.bg-dark
           [:a.navbar-brand {:href "/logout"} "Dobrodošli: " (sesh/get :username) " Kliknite za logout"]
           [:button.navbar-toggler
            {:aria-label "Toggle navigation",
             :aria-expanded "false",
             :aria-controls "navbarNavDropdown",
             :data-target "#navbarNavDropdown",
             :data-toggle "collapse",
             :type "button"}
            [:span.navbar-toggler-icon]]
           [:div#navbarNavDropdown.collapse.navbar-collapse
            [:ul.navbar-nav
             [:li.nav-item.dropdown
              [:a#navbarDropdownMenuLink.nav-link.dropdown-toggle
               {:aria-expanded "false",
                :aria-haspopup "true",
                :data-toggle "dropdown",
                :href "#"}
               "\n          Filmovi\n        "]
              [:div.dropdown-menu
               {:aria-labelledby "navbarDropdownMenuLink"}
               [:a.dropdown-item {:href "/film"} "Unesi novog"]
               [:a.dropdown-item {:href "/filmovi"} "Izmeni/Izbrisi"]]]

             [:li.nav-item.dropdown
              [:a#navbarDropdownMenuLink.nav-link.dropdown-toggle
               {:aria-expanded "false",
                :aria-haspopup "true",
                :data-toggle "dropdown",
                :href "#"}
               "\n          Glumci\n        "]
              [:div.dropdown-menu
               {:aria-labelledby "navbarDropdownMenuLink"}
               [:a.dropdown-item {:href "/glumac"} "Unesi novog"]
               [:a.dropdown-item {:href "/glumci"} "Izmeni/Izbrisi"]]]]]]
          [:div.limiter
           [:div.container-table100
            [:div.wrap-table100
             body
             ]]]
          "\t\n\t"
          [:script {:src "mvendor/jquery/jquery-3.2.1.min.js"}]
          [:script {:src "mvendor/bootstrap/js/popper.js"}]
          [:script {:src "mvendor/bootstrap/js/bootstrap.min.js"}]
          [:script {:src "mvendor/select2/select2.min.js"}]
          [:script {:src "mjs/main.js"}]]]))
