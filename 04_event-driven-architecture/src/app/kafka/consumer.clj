(ns app.kafka.consumer
  (:import [java.time Duration]
           [org.apache.kafka.clients.consumer KafkaConsumer]))

(def consumer (atom nil))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn setup-consumer []
  (let [c (if (= @consumer nil)
            (swap! consumer (fnil identity (KafkaConsumer.
                                            {"bootstrap.servers" "localhost:9092"
                                             "group.id" "order-service"
                                             "key.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"
                                             "value.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"})))
            @consumer)]
    (.subscribe c ["order.created"])))

(defn poll-events []
  (let [records (.poll @consumer (Duration/ofMillis 1000))]
    (doseq [record records]
      (println "[consumer] received: " (.value record)))))

(defn shutdown-consumer []
  (.close @consumer))
