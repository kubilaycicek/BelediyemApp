package com.ketechsoft.reqtrack.dtos;

import lombok.Data;

@Data
public class TokenDto {
    private String username;
    private String token;

    public TokenDto(String username, String token) {
        this.username=username;
        this.token=token;
    }
}
