package edu.isep.ii3502.kafka.ack;



import edu.isep.ii3502.kafka.misc.TemperatureSensorRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serdes;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerWithAck {
    /**
     * This class instantiate a Java Kafka producer and produce some data to Kafka
     * It simulate json data produced by some temperature sensor ( class TemperatureSensorRecord in misc package)
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Properties props = new Properties();
        props = ProducerConfig.addSerializerToConfig(props, new Serdes.StringSerde().serializer(),  new Serdes.StringSerde().serializer());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");

        // You can modify ACK and retry here
        props.put(ProducerConfig.ACKS_CONFIG, "0"); // Possible value : 0 / 1 / all
        props.put(ProducerConfig.RETRIES_CONFIG, "0"); // Number of retries before raising an exception
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "0"); // The time to wait between each retry

        // Produce the kafka producer with the properties
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // We use the topic with only one partition to easier observe the kafka behavior
        String TOPIC = "my-topic-rf2-p1";
        int index = 0;
        while (true){
            // Create a record
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, TemperatureSensorRecord.generateRecord());

            index++;

            System.out.println(String.format("Sent %d records", index));

        }
    }

}
