package com.example.trie_search_example.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document(collection = "trie_2")
@Getter
@Setter
public class TrieDocument {

    @Id
    private String id;
    private char character;
    private boolean isEndOfWord;
    private List<TrieDocument> children;

    public TrieDocument(char character, boolean isEndOfWord, List<TrieDocument> children) {
        this.character = character;
        this.isEndOfWord = isEndOfWord;
        this.children = children;
    }

}
