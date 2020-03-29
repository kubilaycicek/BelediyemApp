package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.UserDto;
import com.ketechsoft.reqtrack.models.User;
import com.ketechsoft.reqtrack.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Mappings.USER_PATH)
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public List<User> getAllDepartments() {
        return userService.getAll();
    }

    @PostMapping(value = "/")
    public UserDto addUser(@RequestBody UserDto userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/")
    public UserDto updateUser(@RequestBody UserDto userDTO) {
        return userService.updateUser(userDTO);
    }

    @GetMapping("/{userID}")
    public UserDto getUser(@PathVariable(name = "userID") long userID) {
        return userService.getById(userID);
    }
}
