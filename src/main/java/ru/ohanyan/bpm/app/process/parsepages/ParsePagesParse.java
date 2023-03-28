package ru.ohanyan.bpm.app.process.parsepages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.ohanyan.bpm.app.repo.PageService;

/**
 * todo Document type ParsePagesParse
 */
@Slf4j
@RequiredArgsConstructor
@Component("parsePagesParse")
public class ParsePagesParse implements JavaDelegate {
    private final RuntimeService runtimeService;

    private final PageService pageService;


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("PPP");
        pageService.parsePages();

    }
}
