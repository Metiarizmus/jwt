package com.lessons.api.repo;

import com.lessons.api.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepo extends MongoRepository<Book, String> {



}
