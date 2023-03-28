package ru.ohanyan.bpm.fw.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.repo.UserService;
import ru.ohanyan.bpm.domain.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * todo Document type UserDetailsService
 */
@RequiredArgsConstructor
@Service
public class UserAuthDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getByLogin(username);
            List<GrantedAuthority> grantedAuthorities = user.getPrivileges().stream().map(e -> new SimpleGrantedAuthority(e.getPrivilegeName())).collect(
                    Collectors.toList());
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
            return new UserAuthDetails(user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getTelegramId(), grantedAuthorities);
        } catch (EntityNoExistsException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
