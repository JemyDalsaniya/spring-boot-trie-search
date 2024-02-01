package com.example.trie_search_example.service;


import com.example.trie_search_example.entity.TrieDocument;
import com.example.trie_search_example.util.Trie;
import com.example.trie_search_example.util.TrieNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrieService {

    private final Trie trie;

    @Autowired
    public TrieService(Trie trie) {
        this.trie = trie;
    }

    public void insert(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return trie.search(word);
    }

    private TrieDocument convertToTrieDocument(TrieNode node) {
        return new TrieDocument(node.getCharacter(), node.isEndOfWord(),
                convertChildrenToTrieDocuments(node.getChildren()));
    }

    private List<TrieDocument> convertChildrenToTrieDocuments(List<TrieNode> children) {
        List<TrieDocument> trieDocuments = new ArrayList<>();
        for (TrieNode child : children) {
            trieDocuments.add(convertToTrieDocument(child));
        }
        return trieDocuments;
    }
}
