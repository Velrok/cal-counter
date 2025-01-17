(ns cal-counter.frontend.app
  (:require [reagent.core :as r]
            ["react" :as react]
            ["react-dom/client" :as react-dom]))

(defn app
  []
  [:h1 "hello"])

(defn init []
  (let [root (react-dom/createRoot (.getElementById js/document "root"))]
    (.render root (r/as-element [app])))
  (println "init done"))
