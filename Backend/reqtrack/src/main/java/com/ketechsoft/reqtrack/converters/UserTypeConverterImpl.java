package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.UserTypeDto;
import com.ketechsoft.reqtrack.models.UserType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserTypeConverterImpl implements UserTypeConverter {

    private final ModelMapper modelMapper;

    @Override
    public UserTypeDto convertToUserTypeDto(UserType userType) {
        if (userType == null)
            return null;
        return modelMapper.map(userType, UserTypeDto.class);
    }

    @Override
    public UserType convertToUserType(UserTypeDto userTypeDto) {
        if (userTypeDto == null)
            return null;
        return modelMapper.map(userTypeDto, UserType.class);
    }
}
