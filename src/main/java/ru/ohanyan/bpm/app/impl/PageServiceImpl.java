package ru.ohanyan.bpm.app.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ohanyan.bpm.adapter.pageparser.PageParser;
import ru.ohanyan.bpm.app.exceptions.DublicateEntityException;
import ru.ohanyan.bpm.app.exceptions.EntityCreationException;
import ru.ohanyan.bpm.app.repo.PageRepository;
import ru.ohanyan.bpm.app.repo.PageService;
import ru.ohanyan.bpm.app.repo.ParsingResultService;
import ru.ohanyan.bpm.domain.Page;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;
    private final PageParser pageParser;
    private final ParsingResultService parsingResultService;

    @Transactional
    @Override
    public void add(String name, String url, String parsingXpath) throws DublicateEntityException, EntityCreationException {
        if (pageRepository.existsByName(name)) {
            throw new DublicateEntityException("Page exists");
        } else {
            try {
                pageRepository.save(new Page(name, URI.create(url).toURL(), parsingXpath));
            } catch (MalformedURLException e) {
                throw new EntityCreationException("Ошибка при создании сущности");
            }
        }
    }

    @Override
    public List<Page> getAllPages() {
        return pageRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        pageRepository.deleteById(id);
    }

    @Override
    public void parsePages() {
        List<Page> pageList = getAllPages();
        for (Page page : pageList) {
            try {
                String result = pageParser.getUrlContent(String.valueOf(page.getUrl()), page.getParsingXpath());
                if (!parsingResultService.checkDoubleResult(page, result)) {
                    parsingResultService.add(page, result);
                }
            } catch (IOException e) {
                log.error("Не удалось распарсить страницу", e);
            }
        }
    }

    @Override
    public void updateXpath(String name, String xPath) {
        if (pageRepository.existsByName(name)) {
            Page page = pageRepository.findByName(name);
            page.setParsingXpath(xPath);
            pageRepository.save(page);
        } else {
            log.error("Сайт не существует");
        }
    }
}

