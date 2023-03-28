package ru.ohanyan.bpm.adapter.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * todo Document type KafkaSender
 */
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    String kafkaTopic = "bpm-kafka";

    public void send(String message) {
        kafkaTemplate.send(kafkaTopic, message);
    }
}
