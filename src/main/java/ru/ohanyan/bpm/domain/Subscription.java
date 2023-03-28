package ru.ohanyan.bpm.domain;

import lombok.*;

import javax.persistence.*;

/**
 * todo Document type Subscription
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
   /* @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_subscriptions_id")
    @SequenceGenerator(name = "sq_subscriptions_id", allocationSize = 1)*/
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_login")
    private User user;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public Subscription(User user, Page page) {
        this.user = user;
        this.page = page;
    }
}
