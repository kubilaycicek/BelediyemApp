package com.ketechsoft.reqtrack.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Complaint Status Data Transfer Object")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintStatusDto extends BaseDto {
    @ApiModelProperty(value = "NAME")
    private String name;
    @ApiModelProperty(value = "DESCRIPTION")
    private String description;
}
