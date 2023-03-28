package ru.ohanyan.bpm.app.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ohanyan.bpm.domain.Page;
import ru.ohanyan.bpm.domain.ParsingResult;
import ru.ohanyan.bpm.domain.Subscription;
import ru.ohanyan.bpm.domain.User;
import ru.ohanyan.bpm.fw.BpmApplication;

import java.util.List;
import java.util.Map;

/**
 * todo Document type UserRepositoryTest
 */
@SpringBootTest(classes = BpmApplication.class)
@Slf4j
class ParsingResultRepositoryTest {

    @Autowired
    private ParsingResultRepository parsingResultRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private PageRepository pageRepository;

    @Test
    void name() {
        // List<ParsingResult> parsingResults = parsingResultRepository.getAllParsingResults();
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        List<ParsingResult> parsingResults = parsingResultRepository.findAll();
        List<Page> pages = pageRepository.findAll();
        List<User> users = userRepository.findAll();

        Map<ParsingResult, List<User>> parsingResultListMap = parsingResultRepository.listOfUsersToSendParsingResult();
        System.out.println(parsingResultListMap);

    }

}
