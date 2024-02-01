package com.example.trie_search_example.util;

import com.example.trie_search_example.entity.TrieDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
            TrieNode next = findChild(current, c);
            if (next == null) {
                next = new TrieNode();
                next.setId(ObjectId.get().toString());
                next.setCharacter(c);
                current.getChildren().add(next);
            }
            current = next;
        }

        current.setEndOfWord(true);

        // Only insert into MongoDB if isEndOfWord is true
        if (current.isEndOfWord()) {
            mongoTemplate.save(convertToTrieDocument(root));
        }
    }

    private TrieNode findChild(TrieNode node, char c) {
        for (TrieNode child : node.getChildren()) {
            if (child.getCharacter() == c) {
                return child;
            }
        }
        return null;
    }

    private TrieDocument convertToTrieDocument(TrieNode node) {
        TrieDocument trieDocument = new TrieDocument(node.getCharacter(), node.isEndOfWord(), null);
        trieDocument.setChildren(convertChildrenToTrieDocuments(node.getChildren()));
        return trieDocument;
    }

    private List<TrieDocument> convertChildrenToTrieDocuments(List<TrieNode> children) {
        List<TrieDocument> trieDocuments = new ArrayList<>();
        for (TrieNode child : children) {
            trieDocuments.add(convertToTrieDocument(child));
        }
        return trieDocuments;
    }

    public boolean search(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            TrieNode next = findChild(current, c);
            if (next == null) {
                return false;
            }
            current = next;
        }

        return current.isEndOfWord();
    }

    public TrieNode getRoot() {
        return root;
    }


}
