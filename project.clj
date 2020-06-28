(defproject wxpush "0.1.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :deploy-repositories [["clojars" {:url "https://clojars.org/repo"
                                    :username :env/clojars_user
                                    :password :env/clojars_pass
                                    :sign-releases false}]]

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clj-http "3.10.1"]
                 [com.taoensso/timbre "4.10.0"]    ; logging
                 [cheshire "5.10.0"]
                 ]
  :repl-options {:init-ns wxpush.core})
