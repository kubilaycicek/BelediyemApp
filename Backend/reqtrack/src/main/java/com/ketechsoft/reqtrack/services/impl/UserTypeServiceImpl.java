package com.ketechsoft.reqtrack.services.impl;

import com.ketechsoft.reqtrack.converters.UserTypeConverter;
import com.ketechsoft.reqtrack.dtos.UserTypeDto;
import com.ketechsoft.reqtrack.models.UserType;
import com.ketechsoft.reqtrack.repositories.UserTypeRepository;
import com.ketechsoft.reqtrack.services.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;
    private final UserTypeConverter userTypeConverter;

    @Override
    public UserTypeDto addUserType(UserTypeDto userTypeDto) {
        UserType userType = userTypeConverter.convertToUserType(userTypeDto);
        return userTypeConverter.convertToUserTypeDto(userTypeRepository.save(userType));
    }

    @Override
    public UserTypeDto updateUserType(UserTypeDto userTypeDto) {
        UserType userType = userTypeRepository.findById(userTypeDto.getId());
        if (userType == null)
            throw new IllegalArgumentException("UserType does not exist ID: " + userTypeDto.getId());
        userType.setName(userTypeDto.getName());
        userType.setDescription(userTypeDto.getDescription());

        return userTypeConverter.convertToUserTypeDto(userTypeRepository.save(userType));
    }

    @Override
    public UserTypeDto getById(long userTypeId) {
        UserType userType = userTypeRepository.findById(userTypeId);
        System.out.println(userType.getName());
        return userTypeConverter.convertToUserTypeDto(userType);
    }

    @Override
    public List<UserType> getAll() {
        ArrayList<UserType> list = new ArrayList<>();
        userTypeRepository.findAll().iterator().forEachRemaining(userType -> list.add(userType));
        return list;
    }
}
