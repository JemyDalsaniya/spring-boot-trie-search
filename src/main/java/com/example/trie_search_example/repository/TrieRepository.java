package com.example.trie_search_example.repository;

import com.example.trie_search_example.entity.TrieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TrieRepository extends MongoRepository<TrieEntity, String> {

    List<TrieEntity> findByWordStartingWith(String prefix);

}
