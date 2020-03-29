package com.ketechsoft.reqtrack.services.impl;

import com.ketechsoft.reqtrack.converters.CategoryConverter;
import com.ketechsoft.reqtrack.dtos.CategoryDto;
import com.ketechsoft.reqtrack.models.Category;
import com.ketechsoft.reqtrack.models.ComplaintGallery;
import com.ketechsoft.reqtrack.models.Department;
import com.ketechsoft.reqtrack.repositories.CategoryRepository;
import com.ketechsoft.reqtrack.repositories.DepartmentRepository;
import com.ketechsoft.reqtrack.services.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = categoryConverter.convertToCategory(categoryDto);
        Department department = departmentRepository.findById(categoryDto.getDepartmentDto().getId());
        if (department == null)
            throw new IllegalArgumentException("Department does not exist ID: " + categoryDto.getDepartmentDto().getId());
        category.setDepartment(department);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryConverter.convertToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {

        Category category = categoryRepository.findById(categoryDto.getId());
        if (category == null)
            throw new IllegalArgumentException("Category does not exist ID: " + categoryDto.getId());

        Department department = departmentRepository.findById(categoryDto.getDepartmentDto().getId());
        if (department == null)
            throw new IllegalArgumentException("Department  does not exist ID: " + categoryDto.getDepartmentDto().getId());
        category.setDepartment(department);

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryConverter.convertToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto getById(long categoryId) {
        Category category = categoryRepository.findById(categoryId);
        return categoryConverter.convertToCategoryDto(category);
    }

    @Override
    public List<Category> getAll() {
        ArrayList<Category> list = new ArrayList<>();
        categoryRepository.findAll().iterator().forEachRemaining(category -> list.add(category));
        return list;
    }
}
