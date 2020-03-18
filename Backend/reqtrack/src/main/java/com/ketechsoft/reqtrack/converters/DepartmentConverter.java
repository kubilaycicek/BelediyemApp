package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.DepartmentDto;
import com.ketechsoft.reqtrack.models.Department;

public interface DepartmentConverter {
    DepartmentDto convertToDepartmentDto(Department department);
    Department convertToDepartment(DepartmentDto departmentDto);
}
