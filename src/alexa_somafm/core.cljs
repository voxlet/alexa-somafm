(ns alexa-somafm.core
  (:require [cljs-lambda.util :as lambda]
            [cljs-lambda.context :as ctx]
            [cljs-lambda.macros :refer-macros [deflambda]]
            [cljs.reader :refer [read-string]]
            [cljs.nodejs :as nodejs]))

(def play-response
  {:version 1.0
   :response {:outputSpeech
              {:type "PlainText"
               :text "Playing Fluid on Soma FM"}
              :directives
              [{:type "AudioPlayer.Play"
                :playBehavior "REPLACE_ALL"
                :audioItem
                {:stream {:token "fluid"
                          :url "https://somafm.com/fluid130.pls"
                          :offsetInMilliseconds 0}}}]}})

(defn intent [request]
  (when (= (get-in request [:request :type]) "IntentRequest")
    (get-in request [:request :intent :name])))

(deflambda alexa-somafm-magic
  "This function receives the JSON input from the Alexa function and creates a
  map of the items of interest.  You can then dispatch on the input map to call
  functions to build the proper response map"
  [request-json]
  (let [request (js->clj request-json)]
    (cond
      (= (get-in request [:request :type]) "LaunchRequest") play-response
      (= (intent request) "Play") play-response
      (= (intent request) "AMAZON.ResumeIntent") play-response

      (= (intent request) "AMAZON.PauseIntent")
      {:version 1.0
       :response {:directives
                  [{:type "AudioPlayer.Stop"}]}}

      :else play-response)))

;; You can replace these response maps with calls to other functions that return similar maps.  The maps should correspond to the JSON requirements set forth
;; here:   https://developer.amazon.com/public/solutions/alexa/alexa-skills-kit/docs/alexa-skills-kit-interface-reference#response-format
