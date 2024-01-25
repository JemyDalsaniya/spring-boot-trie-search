package com.example.trie_search_example.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trie")
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrieEntity {

    @Id
    private String id;

    private String word;

    public TrieEntity(String word) {
        this.word = word;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
