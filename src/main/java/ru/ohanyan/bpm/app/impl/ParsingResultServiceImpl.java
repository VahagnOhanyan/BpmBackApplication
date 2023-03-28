package ru.ohanyan.bpm.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ohanyan.bpm.adapter.kafka.KafkaProducer;
import ru.ohanyan.bpm.app.repo.ParsingResultRepository;
import ru.ohanyan.bpm.app.repo.ParsingResultService;
import ru.ohanyan.bpm.domain.Page;
import ru.ohanyan.bpm.domain.ParsingResult;

import java.util.List;

/**
 * todo Document type ParsingResultServiceImpl
 */
@Service
@RequiredArgsConstructor
public class ParsingResultServiceImpl implements ParsingResultService {
    private final ParsingResultRepository parsingResultRepository;
    //  private final TelegramAdapter telegramAdapter;
    private final KafkaProducer kafkaProducer;

    public void add(Page page, String parsingResult) {
        parsingResultRepository.save(new ParsingResult(page, parsingResult));
    }

    @Override
    public void notifyUsersUnsentResults() {
        parsingResultRepository.listOfUsersToSendParsingResult().forEach((parsingResult, users) -> {
            users.forEach(user -> {
                // telegramAdapter.notifyUser(user.getTelegramId(), parsingResult.getResult());
                kafkaProducer.send(user.getLogin() + ":" + parsingResult.getResult());
                markResultSent(parsingResult);
            });
        });
    }

    @Override
    public boolean checkDoubleResult(Page page, String result) {
        return parsingResultRepository.existsParsingResultByPageIdAndResult(page.getId(), result);
    }

    @Override
    public List<ParsingResult> getAllParsingResults() {
        return parsingResultRepository.findAll();
    }

    @Override
    public void deleteAllParsingResults() {
        parsingResultRepository.deleteAll();
    }

    private void markResultSent(ParsingResult parsingResult) {
        parsingResult.setSent(true);
        parsingResultRepository.save(parsingResult);
    }
}
