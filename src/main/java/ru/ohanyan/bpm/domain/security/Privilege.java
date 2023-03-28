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
@Table(name = "privileges")
public class Privilege {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
 /*   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_privileges_id")
    @SequenceGenerator(name = "sq_privileges_id", allocationSize = 1)*/
    private Long privilegeId;
    private String privilegeName;

    public Privilege(String privilegeName) {
        this.privilegeName = privilegeName;
    }
}
