(ns library.tools
  "Support for building deployment artifacts for a project."
  (:use [cljs.closure :only (build)]
        [library.host-page :only (application-host)]
        [library.config :only (cljs-build-opts production-js)])
  (:require [clojure.java.io :as io]))

(defn build-project
  "Emit both a JavaScript file containing the compiled ClojureScript
  application and the host HTML page."
  [config]
  (build "src/cljs" (assoc (cljs-build-opts config)
                      :optimizations :advanced
                      :output-to (str "out/" (production-js config))))
  (spit "out/public/index.html" (application-host config :production)))