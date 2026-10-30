(ns app.kafka.producer
  (:import [org.apache.kafka.clients.producer KafkaProducer ProducerRecord]))

(def producer (atom nil))

(defn setup-producer []
  (swap! producer (fnil identity
                        (KafkaProducer. {"bootstrap.servers" "localhost:9092"
                                         "key.serializer" "org.apache.kafka.common.serialization.StringSerializer"
                                         "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"}))))

(defn publish-event [topic key value]
  (.send @producer (ProducerRecord. topic key value)))

(defn shutdown-producer []
  (.close @producer))
