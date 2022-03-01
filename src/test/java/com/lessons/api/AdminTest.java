package com.lessons.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessons.api.models.Book;
import com.lessons.api.payload.request.BookRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
public class AdminTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createBookTest() throws Exception{
        BookRequest bookRequest = new BookRequest();
        bookRequest.setNameBook("testBook");
        bookRequest.setAuthor("testAuthor");
        bookRequest.setDescription("testing");

        MvcResult response = mockMvc.perform(post("http://localhost/api/admin/createBooks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().is(200)).andReturn();

        String responseBodyJson = response.getResponse().getContentAsString();
        Book book = objectMapper.readValue(responseBodyJson, Book.class);


        assertThat(book.getNameBook(), is(equalTo(bookRequest.getNameBook())));
        assertThat(book.getAuthor(),is(equalTo(bookRequest.getAuthor())));
        assertThat(book.getDescription(),is(equalTo(bookRequest.getDescription())));
    }

    @Test
    public void getAllUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/admin/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").isNotEmpty());
    }
}
