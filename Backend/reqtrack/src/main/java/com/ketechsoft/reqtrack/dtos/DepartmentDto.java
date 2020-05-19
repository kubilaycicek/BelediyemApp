package com.ketechsoft.reqtrack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto extends BaseDto {
    private String name;
    private String description;
    private List<CategoryDto> categoryDTos;
}
