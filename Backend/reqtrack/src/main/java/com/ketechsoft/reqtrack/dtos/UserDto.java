package com.ketechsoft.reqtrack.dtos;


import lombok.Data;

@Data
public class UserDto extends BaseDto {
    private String name;
    private String surname;
    private String phone;
    private String tcNumber;
    private String email;
    private String password;
    private String address;
    private UserTypeDto userTypeDto;
}
