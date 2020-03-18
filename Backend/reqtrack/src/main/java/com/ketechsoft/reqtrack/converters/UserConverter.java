package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.UserDto;
import com.ketechsoft.reqtrack.models.User;

public interface UserConverter {
    UserDto convertToUserDto(User user);
    User convertToUser(UserDto userDto);
}
