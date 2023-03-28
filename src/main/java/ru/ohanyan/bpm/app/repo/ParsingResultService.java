package ru.ohanyan.bpm.app.repo;

import ru.ohanyan.bpm.domain.Page;
import ru.ohanyan.bpm.domain.ParsingResult;

import java.util.List;

/**
 * todo Document type ParsingResRepository
 */

public interface ParsingResultService {

    void add(Page page, String parsingResult);

    void notifyUsersUnsentResults();

    boolean checkDoubleResult(Page page, String result);

    List<ParsingResult> getAllParsingResults();

    void deleteAllParsingResults();
}
