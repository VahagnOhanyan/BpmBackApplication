package ru.ohanyan.bpm.adapter.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.ohanyan.bpm.adapter.telegram.TelegramAdapter;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.repo.UserService;

/**
 * todo Document type KafkaConsumer
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final TelegramAdapter telegramAdapter;
    private final UserService userService;

    @KafkaListener(topics = "bpm-kafka")
    public void consume(String message) {
        log.info(String.format("Message recieved -> %s", message));
        String[] keyValue = message.split(":");

        try {
            String telegramId = userService.getByLogin(keyValue[0]).getTelegramId();
            telegramAdapter.notifyUser(telegramId, keyValue[1]);
        } catch (EntityNoExistsException e) {
            log.error("Ошибка при получении сообщения: " + e);
        }
    }
}
