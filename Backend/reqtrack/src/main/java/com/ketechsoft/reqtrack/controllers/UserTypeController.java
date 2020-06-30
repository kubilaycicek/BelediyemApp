package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.UserTypeDto;
import com.ketechsoft.reqtrack.models.UserType;
import com.ketechsoft.reqtrack.services.UserTypeService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(Mappings.USER_TYPE_PATH)
public class UserTypeController {
    private final UserTypeService userTypeService;

    @GetMapping("/list")
    public ResponseEntity<List<UserType>> getAllUserTypes() {
        return ResponseEntity.ok(userTypeService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<UserTypeDto> addUserType(@RequestBody UserTypeDto userTypeDTO) {
        return ResponseEntity.ok(userTypeService.addUserType(userTypeDTO));
    }

    @PutMapping("/")
    public ResponseEntity<UserTypeDto> updateUserType(@RequestBody UserTypeDto userTypeDTO) {
        return ResponseEntity.ok(userTypeService.updateUserType(userTypeDTO));
    }

    @GetMapping("/{userTypeID}")
    public ResponseEntity<UserTypeDto> getUserType(@PathVariable(name = "userTypeID") long usertypeID) {
        return ResponseEntity.ok(userTypeService.getById(usertypeID));
    }
}
