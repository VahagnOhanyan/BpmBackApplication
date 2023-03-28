package ru.ohanyan.bpm.app.repo;

import ru.ohanyan.bpm.app.exceptions.EntityCreationException;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.exceptions.EntityRemoveException;
import ru.ohanyan.bpm.domain.Subscription;

import java.util.List;

/**
 * todo Document type SubscriptionService
 */
public interface SubscriptionService {
    List<Subscription> getUserSubscriptions(String login);

    void subscribe(String login, String name) throws EntityCreationException;
    void unsubscribe(String login, String name) throws EntityRemoveException;
    List<Subscription> getAllSubscriptions();

    void deleteSubscription(Long id) throws EntityNoExistsException;
}
