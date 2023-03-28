package ru.ohanyan.bpm.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ohanyan.bpm.app.exceptions.DublicateEntityException;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.exceptions.PropertyUpdateException;
import ru.ohanyan.bpm.app.repo.PrivilegeRepository;
import ru.ohanyan.bpm.app.repo.RoleRepository;
import ru.ohanyan.bpm.app.repo.UserRepository;
import ru.ohanyan.bpm.app.repo.UserService;
import ru.ohanyan.bpm.domain.User;
import ru.ohanyan.bpm.domain.security.Privilege;
import ru.ohanyan.bpm.domain.security.Role;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * todo Document type UserServiceImpl
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository;

    @Override
    public void register(String login, String password, String firstName, String lastName, String role, Set<String> privileges, String telegramId)
            throws DublicateEntityException, EntityNoExistsException {
        if (userRepository.existsById(login)) {
            throw new DublicateEntityException("Пользователь с таким логином уже существует");
        } else {
            Set<Privilege> privilegeSet = null;
            if (!privileges.isEmpty()) {
                privilegeSet = validateAndGetUserPrivileges(privileges);
            }
            Role userRole = validateAndGetUserRole(role);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPw = bCryptPasswordEncoder.encode(password);
            userRepository.save(new User(login, encodedPw, firstName, lastName, userRole, privilegeSet, telegramId));
        }
    }

    private Role validateAndGetUserRole(String role) throws EntityNoExistsException {
        if (!roleRepository.existsByRoleName(role)) {
            throw new EntityNoExistsException("Не удалось найти роль");
        } else {
            Role userRole = roleRepository.findByRoleName(role);
            return userRole;
        }
    }

    private Set<Privilege> validateAndGetUserPrivileges(Set<String> privileges) throws EntityNoExistsException {
        Set<Privilege> privilegeSet = new HashSet<>();
        for (String privilege : privileges) {
            if (!privilegeRepository.existsByPrivilegeName(privilege)) {
                throw new EntityNoExistsException("Не удалось найти привилегию");
            } else {
                privilegeSet.add(privilegeRepository.findByPrivilegeName(privilege));
            }
        }
        return privilegeSet;
    }

    @Override
    public boolean validateUser(String login, String password) throws EntityNoExistsException {
        Optional<User> user = userRepository.findById(login);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new EntityNoExistsException("User doesn't exist");
        }
    }

    @Override
    public Optional<User> getByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }

    @Override
    public User getByLogin(String login) throws EntityNoExistsException {
        Optional<User> user = userRepository.findById(login);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNoExistsException("User doesn't exist");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void updatePassword(String login, String newPassword) throws PropertyUpdateException {
        if (userRepository.existsById(login)) {
            User user = userRepository.getById(login);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPw = bCryptPasswordEncoder.encode(newPassword);
            user.setPassword(encodedPw);
            userRepository.save(user);
        } else {
            throw new PropertyUpdateException("При обновлении пароля возникла ошибка");
        }
    }

    @Override
    public void updatePrivileges(String login, Set<String> privileges) throws PropertyUpdateException {
        try {
            if (userRepository.existsById(login)) {
                User user = userRepository.getById(login);
                Set<Privilege> userPrivileges = validateAndGetUserPrivileges(privileges);
                user.setPrivileges(userPrivileges);
                userRepository.save(user);
            }
        } catch (EntityNoExistsException e) {
            throw new PropertyUpdateException("При обновлении роли возникла ошибка");
        }
    }

    @Override
    public void deleteUser(String login) throws EntityNoExistsException {
        Optional<User> user = userRepository.findById(login);
        if (user.isPresent()) {
            userRepository.deleteById(login);
        } else {
            throw new EntityNoExistsException("User doesn't exist");
        }
    }
}
