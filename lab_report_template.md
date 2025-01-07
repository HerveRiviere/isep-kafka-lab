# II 3502 - Introduction to Apache Kafka - Lab report
## Student name


## Question 0: Kafka use cases
Answer...


## Question 1: What’s a Kafka topic?


## Question 2: Can an event inside Kafka be modified?


## Question 3: Can a Kafka cluster still work if the Zookeeper cluster is unavailable?


## Question 4: Based on Prometheus metric, which broker id is receiving the load?


## Question 5: What is a kafka offset?


## Question 6: Is the application fault-tolerant? How to solve the issue?


## Question 7: Is the kafka replication model a master-follower or a master-master?


## Question 8: Is this configuration scalable? Why?


## Question 9: How is the producer choosing partition of a record?


## Question 10: By checking Prometheus, why is one server receiving more messages than others?


## Question 11: Is edu.isep.ii3502.kafka.BasicConsumer a scalable and a fault-tolerant application?


## Question 12 - Bonus: Explain why edu.isep.ii3502.kafka.consumergroup.ConsumerWithinAConsumerGroup is a scalable and fault-tolerant application.


## Question 13 - Bonus: What happen if you launch 5 instances of the ConsumerWithinAConsumerGroup? Are all instances receiving records? Why?


## Question 14 - Bonus: How do you think kafka is storing consumer offset? Is the consumer application delivery an `at-least-once`, `at-most-once` or `exactly-once`? How to have your consumer application `exactly-once`?


## Question 15 - Bonus: Explain how you can partition data to avoid having data inconsistency when you have multiple instances of your application?


## Question 16 - Bonus: Explain the relationship between kafka record key and kafka partition.


## Question 17 - Bonus: Is your application scalable and fault-tolerant with my-topic-rf2-p4 topic, consumer group and key partitioning? Why?


## Question 18 - Bonus: For a topic with multiple partitions, and a consumer reading all partitions. Do you have a global order of records (all records are read in the same order as they were sent)? Why? Which order guarantee do you have?


## Question 19 - Bonus: For all ack settings (0, 1, all) indicate if it is `at-least-once` (can generate duplicates), `at-most-once`(can generate messages lost) or `exactly-once`?
