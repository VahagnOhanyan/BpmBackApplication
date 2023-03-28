package ru.ohanyan.bpm.app.process.parsepages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.ohanyan.bpm.app.repo.ParsingResultService;

/**
 * todo Document type ParsePagesParse
 */
@Slf4j
@RequiredArgsConstructor
@Component("parsePagesNotify")
public class ParsePagesNotify implements JavaDelegate {

    private final ParsingResultService parsingResultService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("PPN");
        parsingResultService.notifyUsersUnsentResults();
    }
}
