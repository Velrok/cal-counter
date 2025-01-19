(ns cal-counter.frontend.app
  (:require [reagent.core :as r]
            ["react" :as react]
            ["react-dom/client" :as react-dom]))

; (def male-daily-min 1700)
; (def male-daily-maintain 2200)

(defn cal-progress [{:keys [cals label]}]
  (let [id  (keyword label)]
    [:div
     [:label {:for id} (str label "(" cals ")")]]))

(defn cal-progress-day [{:keys [cals label]}]
  (cal-progress {:cals cals :label label}))

(defn reload []
  [:button {:on-click #(.reload js/location)} "reload"])

(defn settings []
  [:h2 "Settings"])

(def main-stage
  (r/atom reload))

(defn tabs []
  [:div {:style {:display "grid"
                 :grid-template-columns "1fr 1fr"
                 :grid-template-rows "100%"}}
   [:button {:on-click (fn [] (reset! main-stage reload))} "Counter"]
   [:button {:on-click (fn [] (reset! main-stage settings))} "Settings"]])

(defn app []
  [:div {:style {:display "grid"
                 :grid-template-columns "100%"
                 :grid-template-rows "4em 1fr 60px"
                 :height "97vh"}}
   [:h1 "CalCounter"]
   [@main-stage]
   [tabs]])

(defn init []
  (let [root (react-dom/createRoot (.getElementById js/document "root"))]
    (.render root (r/as-element [app]))))
