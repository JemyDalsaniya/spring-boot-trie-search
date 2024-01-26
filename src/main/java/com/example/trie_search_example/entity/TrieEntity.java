package com.example.trie_search_example.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrieEntity {

    @Id
    private String id;
    private String word;

    public TrieEntity(String word) {
        this.word = word;
    }

}
