package ru.ohanyan.bpm.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

/**
 * todo Document type ParsingResult
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "parsing_results")
public class ParsingResult {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
/*    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_parsing_results_id")
    @SequenceGenerator(name = "sq_parsing_results_id", allocationSize = 1)*/
    private Long id;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;
    private Instant parsingDateTime;
    private String result;
    private boolean sent;

    public ParsingResult(Page page, String parsingResult) {
        this.page = page;
        this.parsingDateTime = Instant.now();
        this.result = parsingResult;
        this.sent = false;
    }
}
