package ru.ohanyan.bpm.app.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ohanyan.bpm.app.impl.UserServiceImpl;
import ru.ohanyan.bpm.domain.User;
import ru.ohanyan.bpm.fw.BpmApplication;

import javax.transaction.Transactional;
import java.util.HashSet;

/**
 * todo Document type UserServiceImplTest
 */
@Slf4j
@SpringBootTest(classes = BpmApplication.class)
public class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Test
    void register_success() throws Exception {
        userService.register("login6", "b", "c", "d", "USER", new HashSet<>(), "e");
        User user = userRepository.findById("login6").orElseThrow();
        Assertions.assertEquals("b", user.getPassword());
        Assertions.assertEquals("e", user.getTelegramId());
    }
}
