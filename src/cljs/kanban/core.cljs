(ns kanban.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"}))

(defn greeting []
  [:h1 (:text @app-state)])

(defn Card []
  [:div.card
   "a card"])

(defn Column []
  [:div.column
   [:h2 "a column"]])

(defn Board []
  [:div.board
   [Column]
   [Column]])

(reagent/render [Board] (js/document.getElementById "app"))

;; Original render line
;; (reagent/render [greeting] (js/document.getElementById "app"))
