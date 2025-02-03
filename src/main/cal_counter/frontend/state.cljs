(ns cal-counter.frontend.state
  (:require [reagent.core :as r]
            [clojure.edn :as edn]))

(def min-calories
  (r/atom (or (.getItem js/localStorage "min-calories")
              2000)))

(def max-calories
  (let [store-key   "max-calories"
        stored-item (.getItem js/localStorage store-key)]
    (r/atom (or stored-item 2500))))

(defn save-min-calories []
  (.setItem js/localStorage "min-calories" @min-calories))

(defn save-max-calories []
  (.setItem js/localStorage "max-calories" @max-calories))

(defn read-food-entries []
  (-> js/localStorage
      (.getItem "food-entries")
      (edn/read-string)))

(defn write-food-entries [entries]
  (->> entries
       prn-str
       (.setItem js/localStorage "food-entries")))

(def food-entries
  (r/atom (or (read-food-entries)
              [])))

(add-watch food-entries
           :write-food-entries
           (fn [_key _atom _old new]
             (println "Saving all them bananas!")
             (write-food-entries new)))
