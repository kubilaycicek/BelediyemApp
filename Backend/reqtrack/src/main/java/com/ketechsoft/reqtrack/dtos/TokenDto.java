package com.ketechsoft.reqtrack.dtos;

import lombok.Data;

@Data
public class TokenDto {
    private long id;
    private String username;
    private String token;

    public TokenDto(long id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }
}
