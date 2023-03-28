package ru.ohanyan.bpm.adapter.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ohanyan.bpm.domain.User;

import java.util.Optional;


public interface CommandHandler {

    void handlerMesaage(Update update, Optional<User> user, SendMessage response);
}
