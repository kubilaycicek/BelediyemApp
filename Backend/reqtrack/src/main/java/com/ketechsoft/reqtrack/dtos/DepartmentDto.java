package com.ketechsoft.reqtrack.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@ApiModel(value = "Department Data Transfer Object")
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto extends BaseDto {
    @ApiModelProperty(value = "NAME")
    private String name;
    @ApiModelProperty(value = "DESCRIPTION")
    private String description;
    @ApiModelProperty(value = "CATEGORIES")
    private List<CategoryDto> categoryDTos;
}
