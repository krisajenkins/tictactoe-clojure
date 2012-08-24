(ns tictactoe-clojure.test.core
  (:use [clojure.test]
        [tictactoe-clojure.core]))

(deftest test-empty-board
  (is (= empty-board
         [[:_ :_ :_]
          [:_ :_ :_]
          [:_ :_ :_]])))

(deftest test-basic-opening-moves
  (is (= (move empty-board 0 0) [[:X :_ :_]
                                 [:_ :_ :_]
                                 [:_ :_ :_]]))
  (is (= (move empty-board 2 1) [[:_ :_ :_]
                                 [:_ :_ :_]
                                 [:_ :X :_]])))

(deftest test-subsequent-moves
  (is (= (-> empty-board
             (move 0 0)
             (move 1 1)
             (move 0 2))
         [[:X :_ :X]
          [:_ :O :_]
          [:_ :_ :_]]))

  (is (= (move [[:X :X :_]
                [:O :O :_]
                [:_ :_ :_]]
               0 2)
         [[:X :X :X]
          [:O :O :_]
          [:_ :_ :_]])))

(deftest test-status-empty
  (is (= :empty (status empty-board))))

(deftest test-status-ongoing
  (is (= :ongoing (-> empty-board
                      (move 0 0)
                      status)))

  (is (= :ongoing (-> empty-board
                      (move 0 0)
                      (move 0 1)
                      (move 0 2)
                      status)))

  (is (= :ongoing (status [[:X :X :_]
                           [:O :O :_]
                           [:X :O :_]]))))

(deftest test-status-draw
  (is (= :draw (status [[:X :X :O]
                        [:O :X :X]
                        [:X :O :O]]))))

(deftest test-status-win-horizontal
  (is (= :win (status [[:O :O :_]
                       [:X :X :X]
                       [:_ :_ :_]])))

  (is (= :win (status [[:O :O :_]
                       [:_ :_ :_]
                       [:X :X :X]]))))
