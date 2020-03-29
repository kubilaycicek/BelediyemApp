package com.ketechsoft.reqtrack.services.impl;

import com.ketechsoft.reqtrack.converters.DepartmentConverter;
import com.ketechsoft.reqtrack.dtos.DepartmentDto;
import com.ketechsoft.reqtrack.models.Department;
import com.ketechsoft.reqtrack.repositories.DepartmentRepository;
import com.ketechsoft.reqtrack.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = departmentConverter
                .convertToDepartment(departmentDto);
        return departmentConverter
                .convertToDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {

        Department department = departmentRepository.findById(departmentDto.getId());
        if (department == null)
            throw new IllegalArgumentException("Department does not exist ID: " + departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());

        return departmentConverter
                .convertToDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto getById(long departmentId) {
        return departmentConverter
                .convertToDepartmentDto(departmentRepository.findById(departmentId));
    }

    @Override
    public List<Department> getAll() {
        ArrayList<Department> list = new ArrayList<>();
        departmentRepository.findAll().iterator().forEachRemaining(department -> list.add(department));
        return list;
    }
}
