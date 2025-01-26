(ns cal-counter.frontend.app
  (:require [reagent.core :as r]
            ["react" :as react]
            ["react-dom/client" :as react-dom]
            [cal-counter.frontend.ui :as ui]
            [cal-counter.frontend.state :as state]))

; (def male-daily-min 1700)
; (def male-daily-maintain 2200)

(defn settings []
  [:div
   [:h2 "Settings"]
   [:p "Target calorie min"]
   [ui/input state/min-calories]
              
   [:p "Target calorie max"]
   [ui/input state/max-calories]
   [:button {:on-click
             (fn []
               (state/save-min-calories)
               (state/save-max-calories))}
    "Save"]])

(def main-stage
  (r/atom settings))

(defn tabs []
  [:div {:style {:display "grid"
                 :grid-template-columns "1fr 1fr"
                 :grid-template-rows "100%"}}
   [:button {:on-click (fn [] (reset! main-stage ui/reload))} "Counter"]
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
  (println "innit")
  (let [root (react-dom/createRoot (.getElementById js/document "root"))]
    (.render root (r/as-element [app]))))
