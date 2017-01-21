(ns kanban.core
  (:require [reagent.core :as reagent]))

(enable-console-print!)

(def app-state
  (reagent/atom
   {:columns
    [{:title "Todos"
      :cards [{:title "Learn about Reagent"}
              {:title "Tell my friends about Lambda Island"}]}
     {:title "Doing"
      :cards [{:title "Following the Reagent tutorial"}
              {:title "Using Evil mode badly"
               :editing true}]
      :editing true}]}))

;; (defn greeting []
;;   [:h1 (:text @app-state)])

(defn Card [card]
  (if (:editing card)
    [:div.card.editing [:input {:type "text" :value (:title card)}]]
    [:div.card (:title card)]))

(defn NewCard []
  [:div.new-card
   "+ add new card"])

(defn Column [{:keys [title cards editing]}]
  [:div.column
   (if editing
     [:input {:type "text" :value title}]
     [:h2 title])
   (for [c cards]
     [Card c])
   [NewCard]])

(defn NewColumn []
  [:div.new-column
   "+ add new column"])

(defn Board [state]
  [:div.board
   (for [c (:columns @state)]
     [Column c])
   [NewColumn]])

(reagent/render [Board app-state] (js/document.getElementById "app"))

;; Original render line
;; (reagent/render [greeting] (js/document.getElementById "app"))
