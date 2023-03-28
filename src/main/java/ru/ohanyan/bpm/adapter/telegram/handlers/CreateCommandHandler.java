package ru.ohanyan.bpm.adapter.telegram.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ohanyan.bpm.app.exceptions.DublicateEntityException;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.repo.UserService;
import ru.ohanyan.bpm.domain.User;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * todo Document type CreateCommandHandler
 */
@Component("createCommandHandler")
@RequiredArgsConstructor
public class CreateCommandHandler implements CommandHandler {
    private final UserService userService;

    @Override
    public void handlerMesaage(Update update, Optional<User> user, SendMessage response) {

        String login = String.valueOf(update.getMessage().getText());
        String firstName = update.getMessage().getFrom().getFirstName();
        String lastName = update.getMessage().getFrom().getLastName();
        String telegramId = String.valueOf(update.getMessage().getChatId());
        String roleUser = "USER";
        Set<String> privileges = new HashSet<>();
        privileges.add("");
        try {
            userService.register(login, "", firstName, lastName, roleUser, privileges, telegramId);
            response.setText("Введите пароль:");
        } catch (DublicateEntityException | EntityNoExistsException e) {
            response.setText("Логин уже занят, попробуйте другой");
        }
    }
}

