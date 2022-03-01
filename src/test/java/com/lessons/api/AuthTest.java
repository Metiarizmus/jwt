package com.lessons.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessons.api.payload.request.LoginRequest;
import com.lessons.api.payload.request.SignupRequest;
import com.lessons.api.payload.response.JwtResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void correctLoginTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admin");


        MvcResult response = mockMvc.perform(post("http://localhost/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().is(200)).andReturn();

        String responseBodyJson = response.getResponse().getContentAsString();
        JwtResponse jwtResponse = objectMapper.readValue(responseBodyJson, JwtResponse.class);

        System.out.println(jwtResponse);

        assertThat(jwtResponse.getUsername(), is(equalTo(loginRequest.getUsername())));
        assertThat(jwtResponse.getToken(),is(notNullValue()));
        assertThat(jwtResponse.getRefreshToken(),is(notNullValue()));

    }

    @Test
    public void registrTest() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("test");
        signupRequest.setEmail("test@mail.ru");
        signupRequest.setPassword("test");


        this.mockMvc.perform(post("http://localhost/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().is(200)).andReturn();

    }
}
