package com.example.trie_search_example.controller;

import com.example.trie_search_example.entity.TrieEntity;
import com.example.trie_search_example.service.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<String>> searchWords(@PathVariable String prefix) {
        List<String> words = trieService.search(prefix);
        return ResponseEntity.ok(words);
    }

}
