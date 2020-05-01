(def project-version "0.3.2")

(defproject pp project-version

  :description "A Clojure(Script) Pretty Printer"
  :url "http://pretty-print.net/"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.742"]
                 [cljfmt "0.6.7"]
                 [secretary "1.2.1"]
                 [rum "0.11.5"]
                 [cljsjs/codemirror "5.44.0-1"]
                 [org.clojure/core.async "0.2.385"]
                 [cljsjs/parinfer "1.8.1-0"]
                 [cljsjs/js-beautify "1.6.2-0"]
                 [cljsjs/clipboard "1.5.9-0"]
                 [camel-snake-kebab "0.4.0"]
                 [org.clojure/core.async "1.1.587"]]

  :plugins [[lein-cljsbuild "1.1.8"]
            [lein-npm "0.6.2"]
            [lein-less "1.7.5"]
            [lein-figwheel "0.5.19"]
            [lein-asset-minifier "0.4.6"]
            [lein-pdo "0.1.1"]]

  :source-paths ["src"]

  :npm {:dependencies [[source-map-support "0.4.0"]
                       [ws "0.8.1"]]}

  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  :figwheel {:css-dirs ["resources/public/css"]
             :repl false}

  :minify-assets [[:css {:source "resources/public/css/main.css" :target "resources/public/css/main.min.css"}]]

  :clean-targets ^{:protect false} ["target"
                                    "resources/public/out"
                                    "resources/public/out-min"
                                    "resources/public/js/pretty-print.js"
                                    "resources/public/js/pretty-print.min.js"]

  :cljsbuild {:builds {:client {:source-paths ["src/pp/client"]
                                :figwheel     true
                                :compiler {:main pp.client.core
                                           :output-to "resources/public/js/pretty-print.js"
                                           :output-dir "resources/public/js/out"
                                           :closure-defines {pp.client.data/app-v ~project-version}
                                           :asset-path "js/out"}}

                       :client-min {:source-paths ["src/pp/client"]
                                    :compiler {:main pp.client.core
                                               :output-to "resources/public/js/pretty-print.min.js"
                                               :output-dir "resources/public/js/out-min"
                                               :optimizations :advanced
                                               :closure-defines {pp.client.data/app-v ~project-version}
                                               :pretty-print false}}}}

  :aliases {"dev" ["pdo" ["less" "auto"]
                   ["figwheel" "client"]]
            "prod" ["do"  ["clean"]
                    ["less" "once"]
                    ["minify-assets"]
                    ["cljsbuild" "once" "client-min"]]})
