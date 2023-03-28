package ru.ohanyan.bpm.domain;

import lombok.*;
import ru.ohanyan.bpm.domain.security.Privilege;
import ru.ohanyan.bpm.domain.security.Role;

import javax.persistence.*;
import java.util.Set;

/**
 * todo Document type User
 */
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    private String login;

    private String password;
    private String firstName;
    private String lastName;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_privileges",
            joinColumns = @JoinColumn(name = "user_login"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Set<Privilege> privileges;
    private String telegramId;

    public User(String login) {
        this.login = login;
    }

    public User(String login, String password, String firstName, String lastName, Role role, Set<Privilege> privileges, String telegramId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.privileges = privileges;
        this.telegramId = telegramId;
    }
   
  /*// @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "user")
    @OneToMany(mappedBy = "user")
    List<Subscription> subscriptions = new ArrayList<>();*/
}
