package com.lucaslearning.Workshop_Mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lucaslearning.Workshop_Mongo.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String text);
}
