package ru.ohanyan.bpm.adapter.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo Document type KafkaController
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka/")
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        kafkaProducer.send(message);

        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }
}

