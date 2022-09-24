package com.blog.api.payloads;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
