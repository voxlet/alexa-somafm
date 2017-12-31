(defproject alexa-somafm "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [io.nervous/cljs-lambda "0.3.5"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-doo "0.1.8"]
            [io.nervous/lein-cljs-lambda "0.6.6"]]
  :source-paths ["src"]
  :cljs-lambda
  {:resource-dirs ["static"]
   :functions
   [{:name "alexa-somafm-magic"
     :invoke alexa-somafm.core/alexa-somafm-magic}]}
  :profiles {:cljs
             {:cljsbuild
              {:builds
               {:alexa-somafm
                {:source-paths ["src"]
                 :compiler {:target :nodejs
                            :language-in :ecmascript5}}}}}
             :prod [:project/prod :local/prod]
             :project/prod
             [:cljs
              {:cljsbuild
               {:builds
                {:alexa-somafm
                 {:compiler {:output-to "target/alexa_somafm/alexa-somafm.js"
                             :output-dir "target/alexa_somafm"
                             :source-map "target/alexa_somafm/alexa-somafm.js.map"
                             :optimizations :advanced}}}}}]
             :test
             [:cljs
              {:cljsbuild
               {:builds
                {:alexa-somafm
                 {:source-paths ["test"]
                  :compiler {:output-to "target/alexa_somafm_test/alexa-somafm.js"
                             :output-dir "target/alexa_somafm_test"
                             :optimizations :none
                             :main alexa-somafm.test-runner}}}}}]})
