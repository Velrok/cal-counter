(ns cal-counter.frontend.state
  (:require [reagent.core :as r]))

(def min-calories
  (r/atom (or (.getItem js/localStorage "min-calories") 
              2000)))

(def max-calories
  (r/atom (or (.getItem js/localStorage "max-calories") 
              2500)))

(defn save-min-calories []
  (.setItem js/localStorage "min-calories" @min-calories))

(defn save-max-calories []
  (.setItem js/localStorage "max-calories" @max-calories))

