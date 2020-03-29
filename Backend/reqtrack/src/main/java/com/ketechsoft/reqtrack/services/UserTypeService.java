package com.ketechsoft.reqtrack.services;

import com.ketechsoft.reqtrack.dtos.UserTypeDto;
import com.ketechsoft.reqtrack.models.UserType;

import java.util.List;

public interface UserTypeService {
    UserTypeDto addUserType(UserTypeDto userTypeDto);
    UserTypeDto updateUserType(UserTypeDto userTypeDto);
    UserTypeDto getById(long userTypeId);
    List<UserType> getAll();
}
