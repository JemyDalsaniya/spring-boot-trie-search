package com.example.trie_search_example.util;

import com.example.trie_search_example.entity.TrieDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Trie {
    private TrieNode root;
    private final MongoTemplate mongoTemplate;


    public Trie(MongoTemplate mongoTemplate) {
        this.root = new TrieNode();
        this.mongoTemplate = mongoTemplate;

    }

    public void insert(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            current.getChildren().computeIfAbsent(c, k -> new TrieNode());
            current = current.getChildren().get(c);
        }

        current.setEndOfWord(true);

        // Insert into MongoDB
            mongoTemplate.save(new TrieDocument(word, true));
    }

    private void insertTrieNodeIntoMongoDB(TrieNode node, String prefix, boolean isEndOfWord) {
        for (char c : node.getChildren().keySet()) {
            TrieNode childNode = node.getChildren().get(c);
            String newPrefix = prefix + c;
                mongoTemplate.save(new TrieDocument(newPrefix, true));
            insertTrieNodeIntoMongoDB(childNode, newPrefix, childNode.isEndOfWord());
        }
    }

    public boolean search(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            if (!current.getChildren().containsKey(c)) {
                return false;
            }
            current = current.getChildren().get(c);
        }

        return current.isEndOfWord();
    }

    public TrieNode getRoot() {
        return root;
    }


}
