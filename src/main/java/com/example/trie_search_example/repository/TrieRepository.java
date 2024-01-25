package com.example.trie_search_example.repository;

import com.example.trie_search_example.entity.TrieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrieRepository extends MongoRepository<TrieEntity, String> {
}
