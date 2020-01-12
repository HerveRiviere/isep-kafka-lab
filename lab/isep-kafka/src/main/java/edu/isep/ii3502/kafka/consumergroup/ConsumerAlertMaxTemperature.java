package edu.isep.ii3502.kafka.consumergroup;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerAlertMaxTemperature {

    public static void main(String[] args) {


        Properties props = new Properties();
        props = ConsumerConfig.addDeserializerToConfig(props, new Serdes.StringSerde().deserializer(),  new Serdes.StringSerde().deserializer());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");

        int randomNumber = (int) (Math.random() * 1000);
        String groupId =  "my-group-id-" + randomNumber;
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);


        String TOPIC = "my-topic-rf2-p10";

        // The topic we want to consume
        consumer.subscribe(Arrays.asList(TOPIC));


        // Per default a consumer is consuming data from the tail of the topic you can uncomment following method to play with consumer offset
        //goToTheBeginningOfTheTopic(consumer);
        //goToASpecificOffset(consumer, 50);


        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(r -> {
                String msg = String.format("Record content : %s from topic %s partition %d and offset %d with group-id ", r.value(), r.topic(), r.partition(), r.offset(), groupId);
                System.out.println(msg);
            });

        }
    }

}
