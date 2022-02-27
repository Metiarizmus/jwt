package com.lessons.api.controllers;

import com.lessons.api.models.Book;
import com.lessons.api.models.User;
import com.lessons.api.repo.BookRepo;
import com.lessons.api.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/getBooks")
    public ResponseEntity<Set<Book>> listBooks(Principal principal) {

        Optional<User> user =  userRepo.findByUsername(principal.getName());
        Set<Book> list;
        if (user.isPresent()){
            list = user.get().getBooks();

            return ResponseEntity.ok(list);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getBooks/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable String bookId) {
        Optional<Book> book = bookRepo.findById(bookId);
        if (book.isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(book.get());
    }
}
