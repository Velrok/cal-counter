(ns cal-counter.frontend.state
  (:require [reagent.core :as r]))

(def min-calories
  (r/atom (or (.getItem js/localStorage "min-calories") 2000)))

(def max-calories
  (r/atom (or (.getItem js/localStorage "max-calories") 2500)))
