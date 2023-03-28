package ru.ohanyan.bpm.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ohanyan.bpm.app.exceptions.DublicateEntityException;
import ru.ohanyan.bpm.app.exceptions.EntityCreationException;
import ru.ohanyan.bpm.app.exceptions.EntityNoExistsException;
import ru.ohanyan.bpm.app.repo.PageRepository;
import ru.ohanyan.bpm.app.repo.PageService;
import ru.ohanyan.bpm.domain.Page;

import java.util.List;

/**
 * todo Document type PageRestController
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/bpm/admin/pages")
public class PageRestController {
    private final PageService pageService;
    private final PageRepository pageRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addPage(@RequestBody Page page) {
        try {
            pageService.add(page.getName(), page.getUrl().toString(), page.getParsingXpath());
            return ResponseEntity.ok("ok");
        } catch (DublicateEntityException | EntityCreationException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Page>> getAllPages() {
        return new ResponseEntity<>(pageService.getAllPages(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updatexpath/{name}")
    public ResponseEntity<String> updateXpath(@RequestBody Page page, @PathVariable("name") String name) {
        pageService.updateXpath(name, page.getParsingXpath());
        return ResponseEntity.ok("ok");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deletePage(@PathVariable("name") String name) {
        if (pageRepository.existsByName(name)) {
            pageService.delete(pageRepository.findByName(name).getId());
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body(new EntityNoExistsException("Page doesn't exist").toString());
        }
    }
}