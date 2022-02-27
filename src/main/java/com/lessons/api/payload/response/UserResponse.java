package com.lessons.api.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lessons.api.models.Book;
import com.lessons.api.models.Role;
import com.lessons.api.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private Set<Role> roles;
    private Set<Book> books;


    public static UserResponse convert(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRoles(),
                user.getBooks()
        );
    }
}
