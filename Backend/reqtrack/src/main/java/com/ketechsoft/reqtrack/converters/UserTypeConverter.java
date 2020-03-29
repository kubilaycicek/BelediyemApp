package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.UserTypeDto;
import com.ketechsoft.reqtrack.models.UserType;

public interface UserTypeConverter {
    UserTypeDto convertToUserTypeDto(UserType userType);
    UserType convertToUserType(UserTypeDto userTypeDto);
}
