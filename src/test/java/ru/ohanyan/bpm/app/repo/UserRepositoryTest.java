package ru.ohanyan.bpm.app.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ohanyan.bpm.domain.User;
import ru.ohanyan.bpm.fw.BpmApplication;

import java.util.List;

/**
 * todo Document type UserRepositoryTest
 */
@SpringBootTest(classes = BpmApplication.class)
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void name() {
        log.info("Found users: ");
        for (User user : userRepository.findAll()) {
            log.info("{}", user.getLogin());
        }
    }

    @Test
    void name2() {
        log.info("Found users: ");
        List<User> test1 = userRepository.findByFirstName("test");
        log.info("{}", test1);
        List<User> test2 = userRepository.findByFirstName("another");
        log.info("{}", test2);
    }
}
