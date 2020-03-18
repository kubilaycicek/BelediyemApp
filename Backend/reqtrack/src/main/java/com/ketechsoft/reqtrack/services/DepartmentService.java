package com.ketechsoft.reqtrack.services;

import com.ketechsoft.reqtrack.dtos.DepartmentDto;
import com.ketechsoft.reqtrack.models.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(DepartmentDto departmentDto);
    DepartmentDto getById(long departmentId);
    List<Department> getAll();
}
