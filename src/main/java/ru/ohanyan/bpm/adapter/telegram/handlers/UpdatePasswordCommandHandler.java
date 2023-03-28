package ru.ohanyan.bpm.adapter.telegram.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.ohanyan.bpm.app.exceptions.PropertyUpdateException;
import ru.ohanyan.bpm.app.repo.UserService;
import ru.ohanyan.bpm.domain.User;

import java.util.Optional;

/**
 * todo Document type UpdatePasswordCommandHandler
 */
@Component("updatePasswordCommandHandler")
@RequiredArgsConstructor
public class UpdatePasswordCommandHandler implements CommandHandler {
    private final UserService userService;

    @Override
    public void handlerMesaage(Update update, Optional<User> user, SendMessage response) {
        String login = user.orElseThrow().getLogin();
        String password = update.getMessage().getText().trim().toLowerCase();
        try {
            userService.updatePassword(login, password);
            response.setText("Пароль успешно установлен");
        } catch (PropertyUpdateException e) {
            response.setText("При обновлении пароля возникла ошибка");
        }
    }
}
