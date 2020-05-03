package com.ketechsoft.reqtrack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String name;
    private String surname;
    private String tcNumber;
    private String password;
    private String email;
    private String phone;
}
