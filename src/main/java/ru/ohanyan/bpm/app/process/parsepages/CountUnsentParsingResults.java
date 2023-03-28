package ru.ohanyan.bpm.app.process.parsepages;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ohanyan.bpm.app.repo.ParsingResultRepository;

/**
 * todo Document type CountUnsentParsingResults
 */
@RequiredArgsConstructor
@Component("countUnsentParsingResults")
public class CountUnsentParsingResults {
    private final ParsingResultRepository parsingResultRepository;
    private int count;

    public int getCount() {
        count = parsingResultRepository.listOfUsersToSendParsingResult().size();
        return count;
    }
}
