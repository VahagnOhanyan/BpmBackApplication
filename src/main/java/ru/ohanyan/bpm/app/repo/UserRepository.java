package ru.ohanyan.bpm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ohanyan.bpm.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * todo Document type UserRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByFirstName(String firstName);
    Optional<User> findByTelegramId(String telegramId);
    User findByLogin(String login);



}
