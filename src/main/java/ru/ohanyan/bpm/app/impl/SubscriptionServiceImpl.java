package ru.ohanyan.bpm.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ohanyan.bpm.app.exceptions.EntityCreationException;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.exceptions.EntityRemoveException;
import ru.ohanyan.bpm.app.repo.PageRepository;
import ru.ohanyan.bpm.app.repo.SubscriptionRepository;
import ru.ohanyan.bpm.app.repo.SubscriptionService;
import ru.ohanyan.bpm.app.repo.UserRepository;
import ru.ohanyan.bpm.domain.Page;
import ru.ohanyan.bpm.domain.Subscription;
import ru.ohanyan.bpm.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * todo Document type SubscriptionServiceImpl
 */
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    private final PageRepository pageRepository;

    @Override
    public List<Subscription> getUserSubscriptions(String login) {
        return subscriptionRepository.findByUser_Login(login);
    }

    @Override
    public void subscribe(String login, String name) throws EntityCreationException {
        Optional<User> user = userRepository.findById(login);
        Page page = pageRepository.findByName(name);
        if (user.isPresent() && page != null) {
            subscriptionRepository.save(new Subscription(user.get(), page));
        } else {
            throw new EntityCreationException("Ошибка при создании сущности");
        }
    }

    @Transactional
    @Override
    public void unsubscribe(String login, String name) throws EntityRemoveException {
        if (userRepository.existsById(login)) {
            subscriptionRepository.deleteByUser_LoginAndPage_Name(login, name);
        } else {
            throw new EntityRemoveException("Ошибка при удалении сущности");
        }
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public void deleteSubscription(Long id) throws EntityNoExistsException {
        subscriptionRepository.deleteById(id);
    }
}

