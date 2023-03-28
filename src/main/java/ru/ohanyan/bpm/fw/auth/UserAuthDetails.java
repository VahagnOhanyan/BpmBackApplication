package ru.ohanyan.bpm.fw.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * todo Document type UserAuthDetails
 */

public class UserAuthDetails implements UserDetails {

    private final String login;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String telegramId;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserAuthDetails(String login, String password, String firstName, String lastName, String telegramId,
            Collection<? extends GrantedAuthority> authorities) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telegramId = telegramId;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelegramId() {
        return telegramId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
