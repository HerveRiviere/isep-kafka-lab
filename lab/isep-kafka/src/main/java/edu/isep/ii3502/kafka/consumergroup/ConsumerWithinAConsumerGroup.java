package edu.isep.ii3502.kafka.consumergroup;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class ConsumerWithinAConsumerGroup {

    public static void main(String[] args) throws InterruptedException {


        Properties props = new Properties();
        props = ConsumerConfig.addDeserializerToConfig(props, new Serdes.StringSerde().deserializer(),  new Serdes.StringSerde().deserializer());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");

        // This time we have a fixed group-id
        String groupId =  "demo-group-id";
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);


        String TOPIC = "my-topic-rf2-p4";

        // The topic we want to consume
        consumer.subscribe(Arrays.asList(TOPIC));



        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(r -> {
                String msg = String.format("Record content : %s from topic %s partition %d and offset %d with group-id ", r.value(), r.topic(), r.partition(), r.offset(), groupId);
                System.out.println(msg);
            });
            consumer.assignment().stream().forEach(topicPartition -> System.out.println("This consumer is assigned to " + topicPartition));
            // For debug purpose
            Thread.sleep(1000);
        }
    }
}
