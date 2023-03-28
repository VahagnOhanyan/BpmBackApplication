package ru.ohanyan.bpm.adapter.dto;

import lombok.*;

import java.util.Set;

/**
 * todo Document type UserDto
 */
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private Set<String> privileges;
    private String telegramId;
}
