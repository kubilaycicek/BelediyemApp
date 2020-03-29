package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.DepartmentDto;
import com.ketechsoft.reqtrack.models.Department;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DepartmentConverterImpl implements DepartmentConverter {
    private final ModelMapper modelMapper;

    @Override
    public DepartmentDto convertToDepartmentDto(Department department) {
        if (department == null)
            return null;
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public Department convertToDepartment(DepartmentDto departmentDto) {
        if (departmentDto == null)
            return null;
        return modelMapper.map(departmentDto, Department.class);
    }
}
