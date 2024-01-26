package com.example.trie_search_example.controller;

import com.example.trie_search_example.entity.TrieEntity;
import com.example.trie_search_example.service.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/trie")
public class TrieController {

    @Autowired
    private TrieService trieService;

    @PostMapping("/insert")
    public ResponseEntity<String> insertWord(@RequestBody TrieEntity trieEntity) {
        trieService.insert(trieEntity.getWord());
        return ResponseEntity.ok("Word '" + trieEntity.getWord() + "' inserted into the Trie and MongoDB.");
    }

    @GetMapping("/search/{prefix}")
    public ResponseEntity<Object> searchWords(@PathVariable String prefix) {
        Object words = trieService.searchAutoSuggestion(prefix);
        if (words != null) {
            return new ResponseEntity<>(words, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.singletonList("Key not found"), HttpStatus.NOT_FOUND);
        }
    }

}
