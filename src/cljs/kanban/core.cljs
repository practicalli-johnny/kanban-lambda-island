(ns kanban.core
  (:require [reagent.core :as reagent]))

(enable-console-print!)

(defonce app-state (reagent/atom {:text "Hello Chestnut!"}))

(defn greeting []
  [:h1 (:text @app-state)])

(defn Card []
  [:div.card
   "a card"])

(defn NewCard []
  [:div.new-card
   "+ add new card"])

(defn Column []
  [:div.column
   [:h2 "a column"]
   [Card]
   [Card]
   [Card]
   [NewCard]])

(defn NewColumn []
  [:div.new-column
   "+ add new column"])

(defn Board []
  [:div.board
   [Column]
   [Column]
   [NewColumn]])

(reagent/render [Board] (js/document.getElementById "app"))

;; Original render line
;; (reagent/render [greeting] (js/document.getElementById "app"))
