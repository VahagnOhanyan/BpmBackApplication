package ru.ohanyan.bpm.app.repo;

import ru.ohanyan.bpm.app.exceptions.DublicateEntityException;
import ru.ohanyan.bpm.app.exceptions.EntityCreationException;
import ru.ohanyan.bpm.domain.Page;

import java.util.List;

/**
 * todo Document type PageService
 */
public interface PageService {
    void add(String name, String url, String parsingXpath) throws DublicateEntityException, EntityCreationException;
    List<Page> getAllPages();

    void delete(Long id);

     void parsePages();

    void updateXpath(String name, String xPath);

}
