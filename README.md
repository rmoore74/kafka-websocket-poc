# kafka-websocket-poc
Simple proof-of-concept project to explore Spring Boot, Kafka and WebSocket integration.

## Setup
Download and extract Apache Kafka.
```
wget http://mirror.ox.ac.uk/sites/rsync.apache.org/kafka/1.1.0/kafka_2.12-1.1.0.tgz
tar -zxvf kafka_2.12-1.1.0.tgz
```

Start up your Zookeeper server using the default configuration.
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

Start up your Kafka server using the default configuration.
```
bin/kafka-server-start.sh config/server.properties
```

## Build and Run
Build and run the kafka-websocket-poc project using Maven.
```
mvn clean install
java -jar target/kafka-websocket-poc-1.0-SNAPSHOT.jar
```

### Accessing the front-end
Head over to `http://localhost:8080` to access the front-end. Make sure that you create the WebSocket connection by clicking on connect before making any requests to the server.

### Making a request
Open up Postman and create a new POST request to `http://localhost:8080/pay`. Make sure that you set the content type to `Content-Type: application/json`. Then once you have done this, follow this template for the body of the request:
```json
{
  "amount": "20.99",
  "sender": "test-sender",
  "recipient": "test-recipient"
}
```
Watch as the Kafka messages are produced, consumed, and communicated to the front-end via WebSockets.
