package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.UserTypeDto;
import com.ketechsoft.reqtrack.models.UserType;
import com.ketechsoft.reqtrack.services.UserTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Mappings.USER_TYPE_PATH)
@Api(description = "User Type APIs")
public class UserTypeController {
    private final UserTypeService userTypeService;

    @GetMapping("/list")
    @ApiOperation(value = "Get All User Type Operation", response = UserTypeDto.class, responseContainer = "List")
    public ResponseEntity<List<UserType>> getAllUserTypes() {
        return ResponseEntity.ok(userTypeService.getAll());
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Operation", response = UserTypeDto.class)
    public ResponseEntity<UserTypeDto> addUserType(@RequestBody UserTypeDto userTypeDTO) {
        return ResponseEntity.ok(userTypeService.addUserType(userTypeDTO));
    }

    @PutMapping("/")
    @ApiOperation(value = "Update Operation", response = UserTypeDto.class)
    public ResponseEntity<UserTypeDto> updateUserType(@RequestBody UserTypeDto userTypeDTO) {
        return ResponseEntity.ok(userTypeService.updateUserType(userTypeDTO));
    }

    @GetMapping("/{userTypeID}")
    @ApiOperation(value = "Get By Id Operation", response = UserTypeDto.class)
    public ResponseEntity<UserTypeDto> getUserType(@PathVariable(name = "userTypeID") long usertypeID) {
        return ResponseEntity.ok(userTypeService.getById(usertypeID));
    }
}
