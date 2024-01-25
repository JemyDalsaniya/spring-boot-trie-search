package com.example.trie_search_example.service;

//import com.example.trie_search_example.repository.TrieRepository;
import com.example.trie_search_example.entity.TrieEntity;
import com.example.trie_search_example.repository.TrieRepository;
import com.example.trie_search_example.util.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;

        // Save the word in MongoDB
        trieRepository.save(new TrieEntity(word));
//        trieRepository.save(new TrieEntity(word));
    }

    public List<String> search(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return Collections.emptyList();
            }
            node = node.children.get(ch);
        }
        return getAllWordsWithPrefix(node, prefix);
    }

    private List<String> getAllWordsWithPrefix(TrieNode node, String currentPrefix) {
        List<String> words = new ArrayList<>();


        List<TrieEntity> myWords = trieRepository.findAll();

        if (node.isEndOfWord) {
            words.add(currentPrefix);
        }
        for (TrieEntity child : myWords) {

            for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
                words.addAll(getAllWordsWithPrefix(entry.getValue(), currentPrefix + entry.getKey()));
            }
        }

        return words;
    }

    public List<String> autoCompleteSuggestions(String prefix) {
        TrieNode node = getNodeForPrefix(prefix);
        return node != null ? getAllWordsWithPrefix(node, prefix) : Collections.emptyList();
    }

    private TrieNode getNodeForPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return null;
            }
            node = node.children.get(ch);
        }
        return node;
    }




}
