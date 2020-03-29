package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.CategoryDto;
import com.ketechsoft.reqtrack.models.Category;

public interface CategoryConverter {
    CategoryDto convertToCategoryDto(Category category);
    Category convertToCategory(CategoryDto categoryDto);
}
