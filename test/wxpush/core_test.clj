(ns wxpush.core-test
  (:require [clojure.test :refer :all]
            [wxpush.core :refer :all]))

(def token "AT_IgIsA2GnQQsJyuVCdyOU9zoDnZNpxSU8")

(deftest a-test
  (testing "gen code"

    ;; (is (= (:code (make-qrcode token))
    ;;        1000))

    ;; (is (= (:code (send-message token {:text "aaa"
    ;;                                    :uids "bbb"}))
    ;;        1000))

    ))
