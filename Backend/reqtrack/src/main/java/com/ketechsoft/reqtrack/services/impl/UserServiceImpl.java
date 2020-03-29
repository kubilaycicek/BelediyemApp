package com.ketechsoft.reqtrack.services.impl;

import com.ketechsoft.reqtrack.converters.UserConverter;
import com.ketechsoft.reqtrack.dtos.UserDto;
import com.ketechsoft.reqtrack.models.User;
import com.ketechsoft.reqtrack.models.UserType;
import com.ketechsoft.reqtrack.repositories.UserRepository;
import com.ketechsoft.reqtrack.repositories.UserTypeRepository;
import com.ketechsoft.reqtrack.dtos.RegistrationDto;
import com.ketechsoft.reqtrack.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserConverter userConverter;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userConverter.convertToUser(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setUserType(userTypeRepository.findById(userDto.getId()));
        return userConverter.convertToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId());
        if (user != null) {
            user.setName(userDto.getName());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setPhone(userDto.getPhone());
            user.setUsername(userDto.getUsername());
            user.setUserType(userTypeRepository.findById(userDto.getId()));
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            return userConverter.convertToUserDto(userRepository.save(user));
        } else {
            throw new IllegalArgumentException("User does not exist ID: " + userDto.getId());
        }
    }

    @Override
    public UserDto getById(long userID) {
        return userConverter.convertToUserDto(userRepository.findById(userID));
    }

    @Override
    public List<User> getAll() {
        ArrayList<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(user -> list.add(user));
        return list;
    }

    @Transactional
    public Boolean register(RegistrationDto registrationDTO) {
        try {
            User user = new User();
            user.setEmail(registrationDTO.getEmail());
            user.setName(registrationDTO.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
            user.setUsername(registrationDTO.getEmail());
            user.setUserType(userTypeRepository.findById(1));
            userRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            return Boolean.FALSE;
        }
    }
}
