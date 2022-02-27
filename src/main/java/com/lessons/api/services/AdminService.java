package com.lessons.api.services;

import com.lessons.api.enums.CrudOperations;
import com.lessons.api.models.Book;
import com.lessons.api.models.User;
import com.lessons.api.repo.BookRepo;
import com.lessons.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final BookRepo bookRepo;
    private final UserRepo userRepo;


    public boolean crud(String userId, String bookId, CrudOperations crud) {

        Optional<User> user = userRepo.findById(userId);
        Optional<Book> book = bookRepo.findById(bookId);

        if (crud.equals(CrudOperations.ADD) && user.isPresent() && book.isPresent()) {
            user.get().getBooks().add(book.get());
            userRepo.save(user.get());
            return true;
        }

        if (crud.equals(CrudOperations.DELETE) && user.isPresent() && book.isPresent()) {

            user.get().getBooks().remove(book.get());
            userRepo.save(user.get());
            return true;
        }

        return false;
    }
}
