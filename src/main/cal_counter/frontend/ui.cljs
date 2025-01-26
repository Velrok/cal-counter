(ns cal-counter.frontend.ui)

(defn reload []
  [:button {:on-click #(.reload js/location)} "reload"])

(defn input [{:keys [atom label]}]
  [:p
   [:span {:style {:margin-right "1em"}} label]
   [:input {:value @atom
            :type "number"
            :on-change (fn [event]
                         (->> event
                              .-target
                              .-value
                              (reset! atom)))}]])

(defn app-title []
  [:h1#title.title
   {:style {:textAlign "center"}}
   [:img {:src "./img/icon.png"
          :style {:width "1em"
                  :height "1em"
                  :margin-right "0.1em"
                  :display "inline-block"}}]
   "CalCounter"])
