(ns cal-counter.frontend.ui)

(defn reload []
  [:button {:on-click #(.reload js/location)} "reload"])

(defn input [{:keys [atom label]}]
  [:div
   [:p label]
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
   "CalCounter"])
