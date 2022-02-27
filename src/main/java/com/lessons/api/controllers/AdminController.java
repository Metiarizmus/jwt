package com.lessons.api.controllers;

import com.lessons.api.enums.CrudOperations;
import com.lessons.api.models.Book;
import com.lessons.api.models.User;
import com.lessons.api.payload.request.BookRequest;
import com.lessons.api.payload.response.UserResponse;
import com.lessons.api.repo.BookRepo;
import com.lessons.api.repo.UserRepo;
import com.lessons.api.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;


    @PostMapping("/createBooks")
    public ResponseEntity<?> creatBook(@Valid @RequestBody BookRequest bookRequest) {
        Book book = new Book(bookRequest.getAuthor(), bookRequest.getNameBook(), bookRequest.getDescription());
        bookRepo.save(book);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/addBooksToUsers")
    public ResponseEntity<?> addBooksToUser(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) {

        boolean crud = adminService.crud(userId,bookId, CrudOperations.ADD);

        if (!crud) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteBooksFromAUser")
    public ResponseEntity<?> deleteBook(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) {
        boolean crud = adminService.crud(userId,bookId, CrudOperations.DELETE);

        if (!crud) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteBooks/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable String bookId) {
        bookRepo.deleteById(bookId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> users() {
        List<User> list = userRepo.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UserResponse> responses = new ArrayList<>();
        for (User q : list) {
            responses.add(UserResponse.convert(q));
        }
        return ResponseEntity.ok(responses);
    }
}
