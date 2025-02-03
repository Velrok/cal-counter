(ns cal-counter.frontend.state
  (:require [reagent.core :as r]
            [clojure.edn :as edn]))

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

(def food-entries
  (r/atom (or (.getItem js/localStorage "food-entries")
          [])))

(defn write-food-entries []
  (prn-str (.setItem js/localStorage "food-entries" @food-entries)))

(defn read-food-entries []
  (edn/read-string (.getItem js/localStorage "food-entries")))