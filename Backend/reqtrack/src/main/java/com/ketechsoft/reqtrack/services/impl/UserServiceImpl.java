package com.ketechsoft.reqtrack.services.impl;

import com.ketechsoft.reqtrack.converters.UserConverter;
import com.ketechsoft.reqtrack.dtos.RegistrationResultDto;
import com.ketechsoft.reqtrack.dtos.UserDto;
import com.ketechsoft.reqtrack.models.User;
import com.ketechsoft.reqtrack.repositories.UserRepository;
import com.ketechsoft.reqtrack.repositories.UserTypeRepository;
import com.ketechsoft.reqtrack.dtos.RegistrationDto;
import com.ketechsoft.reqtrack.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        if (!isThereUserByTcNumber(userDto.getTcNumber())) {
            User user = userConverter.convertToUser(userDto);
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setUserType(userTypeRepository.findById(userDto.getUserTypeDto().getId()));
            return userConverter.convertToUserDto(userRepository.save(user));
        }else{
            return  null;
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId());
        if (user != null) {
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setPhone(userDto.getPhone());
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
    private boolean isThereUserByTcNumber(String tcNumber){
        User user =userRepository.findByTcNumber(tcNumber);
        if (user!=null){
            return true;
        }else {
            return false;
        }
    }

    public RegistrationResultDto register(RegistrationDto registrationDTO) {
        try {
            if (!isThereUserByTcNumber(registrationDTO.getTcNumber())){
                User user = new User();
                user.setEmail(registrationDTO.getEmail());
                user.setName(registrationDTO.getName());
                user.setSurname(registrationDTO.getSurname());
                user.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
                user.setUserType(userTypeRepository.findById(1));
                user.setTcNumber(registrationDTO.getTcNumber());
                user.setPhone(registrationDTO.getPhone());
                userRepository.save(user);
                return new RegistrationResultDto("succes", true);
            }else{
                return new RegistrationResultDto("unsucces this record is available ", false);
            }
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            return new RegistrationResultDto("unsucces", false);
        }
    }
}