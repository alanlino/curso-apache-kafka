package com.example.testekafka;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@SpringBootApplication
public class TesteKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesteKafkaApplication.class, args);

        // Configurações do produtor Kafka
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Criação do produtor Kafka
        Producer<String, String> producer = new KafkaProducer<>(props);

        // Mensagem a ser enviada
        String topic = "str-topic";
        String key = "chave";
        String value = "Olá, Kafka!";

        // Envio da mensagem
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);

        // Fechamento do produtor
        producer.close();
    }

}
