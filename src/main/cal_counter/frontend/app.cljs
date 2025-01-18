(ns cal-counter.frontend.app
  (:require [reagent.core :as r]
            ["react" :as react]
            ["react-dom/client" :as react-dom]))

(def male-daily-min 1700)
(def male-daily-maintain 2200)

(defn cal-progress [{:keys [cals label p1-max p2-max]}]
  (let [id  (keyword label)
        progress-1 (min cals p1-max)

        progress-2-max (- p2-max p1-max)
        progress-2 (min (- cals p1-max) progress-2-max)

        progress-3 (- cals p2-max)]
    [:div
     [:label {:for id} (str label "(" cals ")")]
     [:div {:style {:display "grid"
                    ;:grid-template-columns (str male-daily-min "fr " progress-2-max "fr " progress-3)
                    :grid-template-columns "1700fr 500fr 1fr"}}
      [:progress {;:style {:flex-grow male-daily-min :flex-shrink 1 :flex-base "auto"}
                  :style {:accent-color "green"}
                  :id id
                  :value progress-1
                  :max p1-max}]
      [:progress {;:style {:flex-grow progress-2-max :flex-shrink 1 :flex-base "auto"}
                  :style {:accent-color "blue"}
                  :value progress-2
                  :id id
                  :max progress-2-max}]
      [:progress {;:style {:flex-grow progress-3 :flex-shrink 1 :flex-base "auto"}
                  :style {:accent-color "orange"}
                  :value progress-3
                  :id id
                  :max progress-3}]]]))

(defn cal-progress-day [{:keys [cals label]}]
  (cal-progress {:cals cals :label label :p1-max male-daily-min :p2-max male-daily-maintain}))

(defn cal-progress-week [{:keys [cals label]}]
  (cal-progress {:cals cals :label label :p1-max (* male-daily-min 7) :p2-max (* male-daily-maintain 7)}))

(defn app []
  [:div
   [:h1 "CalCounter"]
   [:button {:on-click #(.reload js/location)} "reload"]
   [cal-progress-week {:label "Last Week" :cals (* 7 1900)}]
   [cal-progress-week {:label "This Week" :cals (* 7 3000)}]
   [cal-progress-day {:label "today" :cals 500}]])

(defn init []
  (let [root (react-dom/createRoot (.getElementById js/document "root"))]
    (.render root (r/as-element [app]))))
