package com.lessons.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user1")
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBookByIdAPI() throws Exception {

       mockMvc.perform(MockMvcRequestBuilders
               .get("/api/users/getBooks/{bookId}", "621b7a4ff179841e094d3a62")
               .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(authenticated())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.author").value("bookOneForUser1"));
    }

    @Test
    public void getAllBooksAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/getBooks")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").isNotEmpty());
    }
}
