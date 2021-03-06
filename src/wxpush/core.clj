(ns wxpush.core
  (:require [clj-http.client :as http]
            [cheshire.core :as json]
            [taoensso.timbre :as log]))

(defn wx-users
  [app-token]
  (some-> (http/get
           "http://wxpusher.zjiecode.com/api/fun/wxuser"
           {:content-type :json
            :query-params {:appToken app-token
                           :pageSize 1000}
            :as :json})
          :body
          :data
          :records
          ))

(defn all-uids
  [app-token]
  (->> (wx-users app-token)
       (map :uid)))

(def content-type-map {:md 3
                       :text 1
                       :html 2})

(defn send-message
  "发送消息给用户，不指定uids则发送给所有人"
  ([app-token text] (send-message app-token text nil))
  ([app-token text {:keys [content-type uids]
                    :or {content-type :text}}]
   (let [text (if (> (count text) 20000)
                (subs text 0 20000)
                text)
         uids (cond
                (nil? uids) (all-uids app-token)

                (string? uids) [uids]

                :default uids)]
     (log/info send-message text uids)
     (let [r (-> (http/post
                  "http://wxpusher.zjiecode.com/api/send/message"
                  {:content-type :json
                   :body (json/encode {:appToken app-token
                                       :content text
                                       :contentType (content-type-map content-type)
                                       :uids uids})
                   :as :json})
                 :body)]
       (when-not (:success r)
         (log/warn :send-message text :return r))
       r))))

(defn make-qrcode
  ([app-token] (make-qrcode app-token nil))
  ([app-token {:keys [valid-time extra]
               :or {valid-time 1800
                    extra "message"}}]
   (-> (http/post
        "http://wxpusher.zjiecode.com/api/fun/create/qrcode"
        {:content-type :json
         :body (json/encode {:appToken app-token
                             :extra extra
                             :validTime valid-time})
         :as :json})
       :body)))

