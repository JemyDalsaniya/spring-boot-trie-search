package com.example.trie_search_example.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TrieNode {

    @Id
    private String id;
    private char character;
    private boolean isEndOfWord;
    private List<TrieNode> children;

    public TrieNode() {
        this.children = new ArrayList<>();
        this.isEndOfWord = false;
    }

}
