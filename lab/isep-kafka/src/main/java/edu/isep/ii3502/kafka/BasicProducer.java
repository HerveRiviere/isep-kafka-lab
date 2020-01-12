package edu.isep.ii3502.kafka;



import edu.isep.ii3502.kafka.misc.TemperatureSensorRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serdes;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BasicProducer {
    /**
     * This class instantiate a Java Kafka producer and produce some data to Kafka
     * It simulate json data produced by some temperature sensor ( class TemperatureSensorRecord in misc package)
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Minimal properties to create a Kafka producer : ip of the cluster and serializer (here string)
        Properties props = new Properties();
        props = ProducerConfig.addSerializerToConfig(props, new Serdes.StringSerde().serializer(),  new Serdes.StringSerde().serializer());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");

        // Produce the kafka producer with the properties
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);


        String TOPIC = "my-topic-rf1-p1";
        int index = 0;
        while (true){
            // Create a record
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, TemperatureSensorRecord.generateRecord());

            // The send() method is returning a Future : it means the method is non blocking (asynchronous) and allow to
            // have multiple records 'in flight' : the record was sent but the producer didn't receive yet the acknowledgement
            // (or the failure) of the kafka server
            Future<RecordMetadata> maybeSentRecord = producer.send(record);

            // For debug purpose we will here force the code to be synchronous (so never more than 1 record 'in flight') by calling get on the future
            RecordMetadata sentRecord = maybeSentRecord.get();
            System.out.println(String.format("Message %d sent to topic %s, partition %d and with offset %d", index, sentRecord.topic(), sentRecord.partition(), sentRecord.offset()));
            index++;

            // For debug purpose we just send record each 500 ms
            Thread.sleep(500);
        }
    }

}
