package edu.isep.ii3502.kafka.ack;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerWithRecordCount {

    public static void main(String[] args) {

        // Minimal properties to create a Kafka consumer : ip of the cluster, deserializer (here string) and group id (here random - more on this after)
        Properties props = new Properties();
        props = ConsumerConfig.addDeserializerToConfig(props, new Serdes.StringSerde().deserializer(),  new Serdes.StringSerde().deserializer());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");

        int randomNumber = (int) (Math.random() * 1000);
        String groupId =  "my-group-id-" + randomNumber;
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);


        String TOPIC = "my-topic-rf1-p1";

        // The topic we want to consume
        consumer.subscribe(Arrays.asList(TOPIC));


        int index = 0;
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            index += records.count();
            System.out.println("Consume " + index + " records");
        }

    }
}
