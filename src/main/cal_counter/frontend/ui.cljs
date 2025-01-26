(ns cal-counter.frontend.ui)

(defn reload []
  [:button {:on-click #(.reload js/location)} "reload"])