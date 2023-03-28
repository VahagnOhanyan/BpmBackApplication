package ru.ohanyan.bpm.adapter.hibernate;

import ru.ohanyan.bpm.domain.ParsingResult;
import ru.ohanyan.bpm.domain.User;

import java.util.List;
import java.util.Map;

/**
 * todo Document type ParsingResultCustom
 */
public interface ParsingResultRepositoryCustom {

    List<ParsingResult> getAllParsingResults();

    Map<ParsingResult, List<User>> listOfUsersToSendParsingResult();
}
