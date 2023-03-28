package ru.ohanyan.bpm.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ohanyan.bpm.app.exceptions.EntityCreationException;
import ru.ohanyan.bpm.app.exceptions.EntityRemoveException;
import ru.ohanyan.bpm.app.repo.PageRepository;
import ru.ohanyan.bpm.app.repo.SubscriptionRepository;
import ru.ohanyan.bpm.app.repo.SubscriptionService;
import ru.ohanyan.bpm.domain.Page;
import ru.ohanyan.bpm.domain.Subscription;
import ru.ohanyan.bpm.domain.User;

import java.util.List;

/**
 * todo Document type SubscriptionRestController
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/bpm/admin/subscriptions")
public class SubscriptionRestController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionRepository subscriptionRepository;
    private final PageRepository pageRepository;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable("id") String id) {
        return new ResponseEntity<>(subscriptionService.getUserSubscriptions(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return new ResponseEntity<>(subscriptionService.getAllSubscriptions(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSubscription(@RequestBody Subscription subscription) {
        try {
            User user = subscription.getUser();
            Page page = pageRepository.findByName(subscription.getPage().getName());
            if(!subscriptionRepository.existsByUserAndPage(user, page)) {
                subscriptionService.subscribe(user.getLogin(), page.getName());
                return ResponseEntity.ok("ok");
            }else{
                return ResponseEntity.ok("Подписка уже существует");
            }

        } catch (EntityCreationException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<String> deleteSubscription(@RequestBody Subscription subscription) {
        try {
            subscriptionService.unsubscribe(subscription.getUser().getLogin(), subscription.getPage().getName());
            return ResponseEntity.ok("ok");
        } catch (EntityRemoveException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}
