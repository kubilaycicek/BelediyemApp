package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.UserDto;
import com.ketechsoft.reqtrack.dtos.UserTypeDto;
import com.ketechsoft.reqtrack.models.User;
import com.ketechsoft.reqtrack.models.UserType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserConverterImpl implements UserConverter {

    private final ModelMapper modelMapper;
    private final UserTypeConverter userTypeConverter;

    private void addSkipFieldsForConvertToDto() {
        TypeMap<UserDto, User> typeMap = modelMapper.getTypeMap(UserDto.class, User.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<UserDto, User>() {
                @Override
                protected void configure() {
                    skip(destination.getUserType());
                }
            });
        }
    }

    private void addSkipFieldsForConvertToDomain() {
        TypeMap<User, UserDto> typeMap = modelMapper.getTypeMap(User.class, UserDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<User, UserDto>() {
                @Override
                protected void configure() {
                    skip(destination.getUserTypeDto());
                }
            });
        }
    }

    @Override
    public UserDto convertToUserDto(User user) {
        if (user == null)
            return null;

        addSkipFieldsForConvertToDto();

        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setUserTypeDto(userTypeConverter.convertToUserTypeDto(user.getUserType()));

        return userDto;
    }

    @Override
    public User convertToUser(UserDto userDto) {
        if (userDto == null)
            return null;

        addSkipFieldsForConvertToDomain();

        User user = modelMapper.map(userDto, User.class);
        if (user.getUserType() != null) {
            user.setUserType(userTypeConverter.convertToUserType(userDto.getUserTypeDto()));

        }
        return user;
    }
}
