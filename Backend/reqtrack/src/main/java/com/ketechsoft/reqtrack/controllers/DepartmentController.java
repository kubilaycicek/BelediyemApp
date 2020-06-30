package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.DepartmentDto;
import com.ketechsoft.reqtrack.models.Department;
import com.ketechsoft.reqtrack.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(Mappings.DEPARTMENT_PATH)
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/list")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDTO) {
        return ResponseEntity.ok(departmentService.addDepartment(departmentDTO));
    }

    @PutMapping("/")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDTO) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentDTO));
    }

    @GetMapping("/{departmentID}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable(name = "departmentID") long departmentID) {
        return ResponseEntity.ok(departmentService.getById(departmentID));
    }
}
