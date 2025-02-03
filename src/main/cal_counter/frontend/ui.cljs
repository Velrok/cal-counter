(ns cal-counter.frontend.ui)

(defn reload []
  [:button {:on-click #(.reload js/location)} "reload"])

(defn add-banana-button [{:keys [atom]}]
  [:button {:on-click #(swap! atom
                              conj
                              {:amount 12
                               :measure :unit
                               :ingredient-name "Banana"
                               :calories 300})}
   "add banana"])

(defn input [{:keys [atom label]}]
  [:p
   [:span {:style {:margin-right "1em"}} label]
   [:input {:value @atom
            :type "number"
            :on-change (fn [event]
                         (->> event
                              .-target
                              .-value
                              (reset! atom)))}]])

(defn app-title []
  [:h1#title.title
   {:style {:textAlign "center"}}
   [:img {:src "./img/icon.png"
          :style {:width "1em"
                  :height "1em"
                  :margin-right "0.1em"
                  :display "inline-block"}}]
   "CalCounter"])

(defn entry-as-paragraph [{:keys [amount measure ingredient-name calories]}]
  (let [style {:style {:font-weight :bold}}]
    [:p
     {:on-click (fn []) ;; replace element with edit view
      }
     [:span.amount style amount] " "
     [:span.measure style (if (= measure :unit)
                            ""
                            (str (name measure) " of "))]
     [:span.ingredient-name style ingredient-name] " "
     "calories: "  [:span.calories style calories]]))

(defn entry-edit [{:keys [amount measure ingredient-name calories]}]
  ;; todo
  )

(defn entry-new []
  ;; todo
  )
