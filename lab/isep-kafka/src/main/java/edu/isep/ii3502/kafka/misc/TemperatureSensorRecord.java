package edu.isep.ii3502.kafka.misc;

public class TemperatureSensorRecord {

    public static String generateRecord(){
        int id = (int) (Math.random() * 50);
        int temperature = (int) (25 + (1 - Math.random() * 2)* 10);
        return String.format("{\"sensor_id\":%d, \"temperature\":%d}", id, temperature);
    }
}
