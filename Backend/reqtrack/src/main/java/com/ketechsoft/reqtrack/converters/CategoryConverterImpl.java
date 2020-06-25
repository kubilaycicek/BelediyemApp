package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.CategoryDto;
import com.ketechsoft.reqtrack.models.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryConverterImpl implements CategoryConverter {

    private final ModelMapper modelMapper;
    private final DepartmentConverter departmentConverter;

    private void addSkipFieldsForConvertToDto() {
        TypeMap<CategoryDto, Category> typeMap = modelMapper.getTypeMap(CategoryDto.class, Category.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CategoryDto, Category>() {
                @Override
                protected void configure() {
                    skip(destination.getDepartment());
                }
            });
        }
    }

    private void addSkipFieldsForConvertToDomain() {
        TypeMap<Category, CategoryDto> typeMap = modelMapper.getTypeMap(Category.class, CategoryDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Category, CategoryDto>() {
                @Override
                protected void configure() {
                    skip(destination.getDepartmentDto());
                }
            });
        }
    }

    @Override
    public CategoryDto convertToCategoryDto(Category category) {
        if (category == null)
            return null;

        CategoryDto categoryDto =modelMapper.map(category, CategoryDto.class);
        categoryDto.setDepartmentDto(departmentConverter.convertToDepartmentDto(category.getDepartment()));

        return categoryDto;
    }

    @Override
    public Category convertToCategory(CategoryDto categoryDto) {
        if (categoryDto == null)
            return null;
        return modelMapper.map(categoryDto, Category.class);
    }
}
