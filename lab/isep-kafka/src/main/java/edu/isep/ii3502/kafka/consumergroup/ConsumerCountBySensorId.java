package edu.isep.ii3502.kafka.consumergroup;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

public class ConsumerCountBySensorId {


    public static void main(String[] args) {


        Properties props = new Properties();
        props = ConsumerConfig.addDeserializerToConfig(props, new Serdes.StringSerde().deserializer(),  new Serdes.StringSerde().deserializer());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");

        // We are using the application name as group id
        String groupId =  "count-by-sensor-id" ;
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);


        String TOPIC = "my-topic-rf2-p4";

        // The topic we want to consume
        consumer.subscribe(Arrays.asList(TOPIC));
        JSONParser parse = new JSONParser();

        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            Iterator<ConsumerRecord<String, String>> recordIterator = records.iterator();
            while (recordIterator.hasNext()){
                ConsumerRecord<String, String> record = recordIterator.next();

                try {
                    JSONObject sensorData = (JSONObject)parse.parse(record.value());
		    Long sensorId = (Long) sensorData.get("sensor_id");
                    System.out.println(sensorId);
                    // TODO count number of record by sensor_id
                    
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
