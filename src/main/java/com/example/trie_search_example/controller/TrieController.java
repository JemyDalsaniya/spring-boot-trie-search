package com.example.trie_search_example.controller;

import com.example.trie_search_example.service.TrieService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class TrieController {

    @Autowired
    private TrieService trieService;

    @Hidden
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/insert/{word}")
    public ResponseEntity<String> insertWord(@PathVariable String word) {
        trieService.insert(word);
        return ResponseEntity.status(HttpStatus.CREATED).body("Word inserted successfully");
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<String> searchWord(@PathVariable String word) {
        boolean wordExists = trieService.search(word);
        if (wordExists) {
            return ResponseEntity.ok("Word found");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not found");
        }
    }

}
