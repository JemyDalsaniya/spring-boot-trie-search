package com.example.trie_search_example.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "trie_collection")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrieDocument {

    @Id
    private String id;

    private String prefix;
    private boolean isEndOfWord;

    public TrieDocument(String prefix, boolean isEndOfWord) {
        this.prefix = prefix;
        this.isEndOfWord = isEndOfWord;
    }

}
