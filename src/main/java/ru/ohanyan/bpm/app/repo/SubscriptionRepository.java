package ru.ohanyan.bpm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ohanyan.bpm.domain.Page;
import ru.ohanyan.bpm.domain.Subscription;
import ru.ohanyan.bpm.domain.User;

import java.util.List;

/**
 * todo Document type SubscriptionRepository
 */
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUser_Login(String login);

    void deleteByUser_LoginAndPage_Name(String user, String page);

    void deleteByUser_Login(String user);

    boolean existsByUserAndPage(User user, Page page);
}
