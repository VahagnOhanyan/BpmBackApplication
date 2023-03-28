package ru.ohanyan.bpm.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ohanyan.bpm.adapter.hibernate.ParsingResultRepositoryCustom;
import ru.ohanyan.bpm.domain.ParsingResult;

/**
 * todo Document type ParsingResRepository
 */
@Repository
public interface ParsingResultRepository extends JpaRepository<ParsingResult, Long>, ParsingResultRepositoryCustom {
    boolean existsParsingResultByPageIdAndResult(Long pageId, String result);
}
