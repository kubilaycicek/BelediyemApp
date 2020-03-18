package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.DepartmentDto;
import com.ketechsoft.reqtrack.models.Department;
import com.ketechsoft.reqtrack.services.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Mappings.DEPARTMENT_PATH)
@Api(description = "Department APIs")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/list")
    @ApiOperation(value = "Get All Department Operation", responseContainer = "List", response = DepartmentDto.class)
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Operation", response = DepartmentDto.class)
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDTO) {
        return ResponseEntity.ok(departmentService.addDepartment(departmentDTO));
    }

    @PutMapping("/")
    @ApiOperation(value = "Update Operation", response = DepartmentDto.class)
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDTO) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDTO));
    }

    @GetMapping("/{departmentID}")
    @ApiOperation(value = "Get By Id Operation", response = DepartmentDto.class)
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable(name = "departmentID") long departmentID) {
        return ResponseEntity.ok(departmentService.getById(departmentID));
    }
}
