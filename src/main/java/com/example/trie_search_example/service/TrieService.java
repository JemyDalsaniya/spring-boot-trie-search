package com.example.trie_search_example.service;

import com.example.trie_search_example.entity.TrieEntity;
import com.example.trie_search_example.repository.TrieRepository;
import com.example.trie_search_example.util.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class TrieService {

    @Autowired
    private TrieRepository trieRepository;


    public TrieService(TrieRepository trieRepository) {
        this.trieRepository = trieRepository;
    }

    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.getChildren().computeIfAbsent(ch, k -> new TrieNode());
        }
        node.setEndOfWord(true);
        // Save the word in MongoDB
        trieRepository.save(new TrieEntity(word));
    }

//    public List<String> search(String key) {
//        TrieNode node = findNodeForKey(key);
//        return (node == null) ? Collections.emptyList() : getAllWordsWithPrefix(node, key);
//    }
//
//    private TrieNode findNodeForKey(String key) {
//        TrieNode node = root;
//        for (char ch : key.toCharArray()) {
//            if (!node.getChildren().containsKey(ch)) {
//                return null;
//            }
//            node = node.getChildren().get(ch);
//        }
//        return node;
//    }
//
//    private List<String> getAllWordsWithPrefix(TrieNode node, String key) {
//        List<String> words = new ArrayList<>();
//
//        if (node.isEndOfWord()) {
//            words.add(key);
//        }
//
//        List<TrieEntity> entities = trieRepository.findByWordStartingWith(key);
//        if (entities.isEmpty()) {
//            words.add("Key is not present in Database !");
//        } else {
//            words.addAll(entities.stream().map(TrieEntity::getWord).toList());
//        }
//
//        return words;
//    }

    public Object searchAutoSuggestion(String key) {
        TrieNode current = root;
        boolean isKeyPresent = true;

        // Traverse the trie to check if the complete word is present
        for (char c : key.toCharArray()) {
            current = current.getChildren().get(c);
            if (current == null) {
                isKeyPresent = false;
                break;
            }
        }

        // If the complete word is present, return it
        if (isKeyPresent && current.isEndOfWord()) {
            return key;
        }

        // Otherwise, perform auto-suggestion logic based on startsWith method
        List<TrieEntity> suggestions = trieRepository.findByWordStartingWith(key);
        List<String> autoSuggestions = suggestions.stream()
                .map(TrieEntity::getWord)
                .toList();

        return autoSuggestions.isEmpty() ? "Not present in DataBase" : autoSuggestions;
    }

}
