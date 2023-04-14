## LOCAL SETUP:

## Start Zookeeper Server:

    ```
        bin/zookeeper-server-start.sh config/zookeeper.properties
    ```

## Start Kafka Server:

    ```
        bin/kafka-server-start.sh config/server.properties
    ```

## Kafka Console Producer:

    ```
        bin/kafka-console-producer.sh --topic incident_change --bootstrap-server localhost:9092
    ```

## Maven Install:

    ```
        mvn clean install
    ```

## SpringBoot Run:

    ```
        mvn springboot:run
    ```

## published incident event examples:

    {"title": "new incident1", "description": "Incident 1 description", "address": "address", "phone" : "12345"}
    {"title": "new incident2", "description": "Incident 2 description", "address": "address2", "phone" : "67890"}
    {"title": "new incident3", "description": "Incident 3 description", "address": "address3", "phone" : "67890333"}
