package ru.ohanyan.bpm.domain.security;

import lombok.*;

import javax.persistence.*;

/**
 * todo Document type User
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
/*    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_roles_id")
    @SequenceGenerator(name = "sq_roles_id", allocationSize = 1)*/
    private Long roleId;
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
