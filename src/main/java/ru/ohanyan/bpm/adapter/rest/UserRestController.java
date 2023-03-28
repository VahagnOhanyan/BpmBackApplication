package ru.ohanyan.bpm.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ohanyan.bpm.adapter.dto.UserDto;
import ru.ohanyan.bpm.app.exceptions.DublicateEntityException;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.exceptions.PropertyUpdateException;
import ru.ohanyan.bpm.app.repo.SubscriptionRepository;
import ru.ohanyan.bpm.app.repo.SubscriptionService;
import ru.ohanyan.bpm.app.repo.UserService;
import ru.ohanyan.bpm.domain.User;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("http://localhost:3006")
@RequestMapping("/bpm/admin/users")
public class UserRestController {
    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final SubscriptionRepository subscriptionRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDto user) {
        try {
            userService.register(user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getRole(), user.getPrivileges(),
                    user.getTelegramId());
            return ResponseEntity.ok("ok");
        } catch (DublicateEntityException | EntityNoExistsException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("llllllllllllllll");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getByLogin(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(userService.getByLogin(id), HttpStatus.OK);
        } catch (EntityNoExistsException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateuserdata/{id}")
    public ResponseEntity<String> updateUserData(@RequestBody UserDto user, @PathVariable("id") String id) {
        try {
            userService.updatePassword(id, user.getPassword());
        } catch (PropertyUpdateException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
        try {
            userService.updatePrivileges(id, user.getPrivileges());
        } catch (PropertyUpdateException | EntityNoExistsException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
        return ResponseEntity.ok("ok");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        try {
            subscriptionRepository.deleteByUser_Login(id);
            userService.deleteUser(id);

            return ResponseEntity.ok("ok");
        } catch (EntityNoExistsException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @PutMapping("/validate/{id}")
    public ResponseEntity<String> validateUser(@RequestBody User user, @PathVariable("id") String id) {
        try {
            userService.validateUser(id, user.getPassword());
            return ResponseEntity.ok("ok");
        } catch (EntityNoExistsException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}
