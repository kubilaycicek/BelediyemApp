package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.CategoryDto;
import com.ketechsoft.reqtrack.models.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryConverterImpl implements CategoryConverter {

    private final ModelMapper modelMapper;

    @Override
    public CategoryDto convertToCategoryDto(Category category) {
        if (category == null)
            return null;
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public Category convertToCategory(CategoryDto categoryDto) {
        if (categoryDto == null)
            return null;
        return modelMapper.map(categoryDto, Category.class);
    }
}
