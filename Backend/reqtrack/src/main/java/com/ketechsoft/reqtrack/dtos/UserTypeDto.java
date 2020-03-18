package com.ketechsoft.reqtrack.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "User Type Data Transfer Object")
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeDto extends BaseDto {
    @ApiModelProperty(value = "NAME")
    private String name;
    @ApiModelProperty(value = "DESCRIPTION")
    private String description;
}
