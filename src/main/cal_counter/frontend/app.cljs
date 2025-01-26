(ns cal-counter.frontend.app
  (:require [reagent.core :as r]
            ; ["react" :as react]
            ["react-dom/client" :as react-dom]
            [cal-counter.frontend.ui :as ui]
            [cal-counter.frontend.state :as state]))

; (def male-daily-min 1700)
; (def male-daily-maintain 2200)

(defn settings []
  [:div
   [:h2 "Settings"]

   [:p
    [ui/input
     {:label "Target calorie min"
      :atom state/min-calories}]

    [ui/input
     {:label "Target calorie max"
      :atom state/max-calories}]

    [:button {:on-click
              (fn []
                (state/save-min-calories)
                (state/save-max-calories))}
     "Save"]]

   [:p
    [ui/reload]]])

(def entries
  [{:amount 1
    :measure :link
    :ingredient-name "chorizo"
    :calories 100}
   {:amount 80
    :measure :grams
    :ingredient-name "mayonaise"
    :calories 150}
   {:amount 1
    :measure :unit
    :ingredient-name "peper"
    :calories 5}])

(defn log-food []
  [:div
   [:h2 "Log Food"]
   [:div.entries
    (for [entry entries]
      (ui/entry-as-paragraph entry))]])

(def main-stage
  (r/atom log-food))

(defn tabs []
  [:div {:style {:display "grid"
                 :grid-template-columns "1fr 1fr"
                 :grid-template-rows "100%"}}
   [:button {:on-click (fn [] (reset! main-stage log-food))} "Log Food"]
   [:button {:on-click (fn [] (reset! main-stage settings))} "Settings"]])

(defn app []
  [:div {:style {:display "grid"
                 :grid-template-columns "100%"
                 :grid-template-rows "4em 1fr 60px"
                 :height "97vh"}}
   [ui/app-title]
   [@main-stage]
   [tabs]])

(defn init []
  (let [root (react-dom/createRoot (.getElementById js/document "root"))]
    (.render root (r/as-element [app]))))
