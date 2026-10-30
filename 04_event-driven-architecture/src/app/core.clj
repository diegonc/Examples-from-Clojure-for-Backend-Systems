(ns app.core
  (:import [java.lang Thread])
  (:require [clojure.data.json :as json]
            [app.kafka.producer :refer [setup-producer publish-event, shutdown-producer]]
            [app.kafka.consumer :refer [setup-consumer, poll-events, shutdown-consumer]]))

(defn- publisher []
  (let [n (rand-int 10)]
    (dotimes [i n]
      (Thread/sleep (rand-int 500))
      (publish-event "order.created" "order-123"
                     (json/write-str {:event "OrderCreated"
                                      :order_id 123
                                      :iteration i})))
    n))

(defn- poller []
  (let [n (rand-int 10)]
    (dotimes [_ n]
      (Thread/sleep (rand-int 500))
      (poll-events))
    n))

(defn- run-kafka []
  (setup-producer)
  (setup-consumer)

  (let [p (future (publisher))
        c (future (poller))]
    (println @p)
    (println @c)
    (println "Done")

    (shutdown-consumer)
    (shutdown-producer)))

(defn- run-rabbitmq []
  (println "TODO"))

(defn -main [& args]
  (if (= (first args) "kafka")
    (run-kafka)
    (run-rabbitmq)))
