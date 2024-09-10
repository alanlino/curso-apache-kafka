package com.example.strproducer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Log4j2
@RequiredArgsConstructor
@Service
public class StringProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {

        kafkaTemplate.send("str-topic", message).whenComplete((result, e) -> {
            if (e != null) {
                log.error("Mensagem não enviada: {}", e.getMessage());
            } else {
                log.info("Mensagem enviada: {}", message);
                log.info("Partição: {}",result.getRecordMetadata().partition());
            }
        });

    }
}
