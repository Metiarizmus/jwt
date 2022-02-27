package com.lessons.api.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BookRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String author;

    @NotBlank
    private String nameBook;

    @NotBlank
    private String description;
}
