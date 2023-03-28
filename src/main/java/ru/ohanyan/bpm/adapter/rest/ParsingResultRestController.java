package ru.ohanyan.bpm.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ohanyan.bpm.app.repo.ParsingResultService;
import ru.ohanyan.bpm.domain.ParsingResult;

import java.util.List;

/**
 * todo Document type UserRestController
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/bpm/admin/parsing_result")
public class ParsingResultRestController {
    private final ParsingResultService parsingResultService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ParsingResult>> getAllParsingResults() {
        return new ResponseEntity<>(parsingResultService.getAllParsingResults(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllParsingResults() {
        parsingResultService.deleteAllParsingResults();
        return ResponseEntity.ok("ok");
    }
}

