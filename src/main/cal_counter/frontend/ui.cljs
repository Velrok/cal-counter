(ns cal-counter.frontend.ui)

(defn reload []
  [:button {:on-click #(.reload js/location)} "reload"])

(defn input [atom]
  [:input {:value @atom
           :type "number"
           :on-change (fn [event]
                        (->> event
                             .-target
                             .-value
                             (reset! atom)))}])