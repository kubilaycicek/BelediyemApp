package com.ketechsoft.reqtrack.dtos;

import lombok.Data;

@Data
public class TokenDto {
    private long id;
    private String username;
    private String token;
    private String fullName;

    public TokenDto(long id, String username, String token,String fullName) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.fullName=fullName;
    }
}
