package ru.ohanyan.bpm.adapter.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.ohanyan.bpm.adapter.telegram.handlers.CommandHandler;
import ru.ohanyan.bpm.app.repo.UserService;
import ru.ohanyan.bpm.domain.User;

import java.util.Map;
import java.util.Optional;

/**
 * todo Document type TelegramBotAdapter
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class TelegramBotAdapter extends TelegramLongPollingBot implements TelegramAdapter {
    private static final String CMD_START = "/start";
    private final Map<String, CommandHandler> messageHandlers;
    private final UserService userService;
    @Value("${bot.username}")
    String botName;
    @Value("${bot.token}")
    String botToken;

    @Override
    public void notifyUser(String telegramId, String message) {
        var response = new SendMessage();
        response.setChatId(telegramId);
        response.setText(message);
        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error("Unable to send response", e);
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Message received: {}", update);
        if (update.hasMessage()) {
            var response = new SendMessage();
            response.setChatId(String.valueOf(update.getMessage().getChatId()));
            Optional<User> user = userService.getByTelegramId(String.valueOf(update.getMessage().getChatId()));
            if (CMD_START.equals(update.getMessage().getText())) {
                messageHandlers.get("startCommandHandler").handlerMesaage(update, user, response);
            } else if (user.isPresent()) {
                messageHandlers.get("updatePasswordCommandHandler").handlerMesaage(update, user, response);
            } else {
                messageHandlers.get("createCommandHandler").handlerMesaage(update, user, response);
            }
            try {
                execute(response);
            } catch (TelegramApiException e) {
                log.error("Unable to send response", e);
            }
        }
    }
}
