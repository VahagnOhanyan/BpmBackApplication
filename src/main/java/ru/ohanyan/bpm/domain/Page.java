package ru.ohanyan.bpm.domain;

import lombok.*;

import javax.persistence.*;
import java.net.URL;

/**
 * todo Document type Page
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "pages")
public class Page {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
   /* @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pages_id")
    @SequenceGenerator(name = "sq_pages_id", allocationSize = 1)*/
    private Long id;
    private String name;
    @Column(name = "url")
    private URL url;
    @Column(name = "parsing_x_path")
    private String parsingXpath;

    public Page(String name, URL url, String parsingXpath) {
        this.name = name;
        this.url = url;
        this.parsingXpath = parsingXpath;
    }

    public Page(String name) {
        this.name = name;
    }
}
