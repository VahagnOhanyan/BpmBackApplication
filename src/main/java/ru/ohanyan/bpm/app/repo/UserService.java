package ru.ohanyan.bpm.app.repo;

import ru.ohanyan.bpm.app.exceptions.DublicateEntityException;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.exceptions.PropertyUpdateException;
import ru.ohanyan.bpm.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * todo Document type UserService
 */
public interface UserService {

    void register(String login, String password, String firstName, String lastName, String role, Set<String> privileges, String telegramId)
            throws DublicateEntityException, EntityNoExistsException;

    boolean validateUser(String login, String password) throws EntityNoExistsException;

    Optional<User> getByTelegramId(String telegramId);

    User getByLogin(String login) throws EntityNoExistsException;

    List<User> getAllUsers();

    void updatePassword(String login, String newPassword) throws PropertyUpdateException;

    void updatePrivileges(String login, Set<String> privileges) throws PropertyUpdateException, EntityNoExistsException;

    void deleteUser(String login) throws EntityNoExistsException;
}
