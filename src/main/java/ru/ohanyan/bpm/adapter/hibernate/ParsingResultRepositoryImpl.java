package ru.ohanyan.bpm.adapter.hibernate;

import org.springframework.stereotype.Component;
import ru.ohanyan.bpm.app.repo.PageRepository;
import ru.ohanyan.bpm.app.repo.SubscriptionRepository;
import ru.ohanyan.bpm.app.repo.UserRepository;
import ru.ohanyan.bpm.domain.ParsingResult;
import ru.ohanyan.bpm.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * todo Document type ParsingResImpl
 */
@Component("parsingResultRepositoryImpl")
public class ParsingResultRepositoryImpl implements ParsingResultRepositoryCustom {
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PageRepository pageRepository;

    public ParsingResultRepositoryImpl(EntityManager entityManager, UserRepository userRepository,
            SubscriptionRepository subscriptionRepository, PageRepository pageRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.pageRepository = pageRepository;
    }

    @Override
    public List<ParsingResult> getAllParsingResults() {
        return entityManager.createQuery("select p.id, p.page,p.parsingDateTime,p.result,p.sent from ParsingResult p").getResultList();
    }

    //  @Query(value = "select u.login from User u join Subscription s on s.user.login = u.login join Page p on p.id = s.page.id join ParsingResult pr on pr.page.id = p.id")
    public Map<ParsingResult, List<User>> listOfUsersToSendParsingResult() {

        return entityManager.createQuery(
                        "select pr, u from User u " +
                                "join Subscription s on s.user.login = u.login " +
                                "join Page p on p.id = s.page.id " +
                                "join ParsingResult pr on pr.page.id = p.id where pr.sent = false", Tuple.class)
                .getResultList()
                .stream()
                .collect(groupingBy(tuple -> ((ParsingResult) tuple.get(0)), Collectors.mapping(tuple -> ((User) tuple.get(1)), Collectors.toList())));

    }
}

