(ns kanban.core
  (:require [reagent.core :as reagent]))

(enable-console-print!)

(def board
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

(defn Card [card-cursor]
  (let [{:keys [editing title]} @card-cursor]
    (if editing
      [:div.card.editing [:input {:type "text" :value title}]]
      [:div.card title])))

(defn NewCard []
  [:div.new-card
   "+ add new card"])

(defn Column [column-cursor]
  (let [{:keys [title cards editing]} @column-cursor]
    [:div.column
     (if editing
       [:input {:type "text" :value title}]
       [:h2 title])
     (for [i (range (count cards))]
       [Card (reagent/cursor column-cursor [:cards i])])
     [NewCard]]))

(defn NewColumn []
  [:div.new-column
   "+ add new column"])

(defn Board [board]
  [:div.board
   (for [i (range (count (:columns @board)))]
     [Column (reagent/cursor board [:columns i])])
   [NewColumn]])

;; (def cards-cursor
;;   (reagent/cursor board [:columns 0 :cards]))

;; @cards-cursor

;; (swap! cards-cursor conj {:title "New card in column 0"})

(reagent/render [Board board] (js/document.getElementById "app"))

;; Original render line
;; (reagent/render [greeting] (js/document.getElementById "app"))
