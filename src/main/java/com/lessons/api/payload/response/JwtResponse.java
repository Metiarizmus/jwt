package com.lessons.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String refreshToken;

    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token,String refreshToken, String id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.refreshToken = refreshToken;
    }
}
