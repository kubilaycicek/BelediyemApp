package com.ketechsoft.reqtrack.services;

import com.ketechsoft.reqtrack.dtos.RegistrationDto;
import com.ketechsoft.reqtrack.dtos.RegistrationResultDto;
import com.ketechsoft.reqtrack.dtos.UserDto;
import com.ketechsoft.reqtrack.models.User;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto getById(long userId);
    List<User> getAll();
    RegistrationResultDto register(RegistrationDto registrationDTO);
}
