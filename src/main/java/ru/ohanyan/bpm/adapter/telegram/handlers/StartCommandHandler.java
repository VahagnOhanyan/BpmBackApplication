package ru.ohanyan.bpm.adapter.telegram.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ohanyan.bpm.domain.User;

import java.util.Optional;

/**
 * todo Document type StartCommandHandler
 */
@Component("startCommandHandler")
public class StartCommandHandler implements CommandHandler {
    @Override
    public void handlerMesaage(Update update, Optional<User> user, SendMessage response) {
        if(user.isPresent()) {
            response.setText("Вы уже зарегистрированы, вы можете обновить пароль:");
        } else{
            response.setText("Добро пожаловать! Введите логин:");
        }
    }
}
