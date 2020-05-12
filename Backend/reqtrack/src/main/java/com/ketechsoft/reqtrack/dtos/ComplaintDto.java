package com.ketechsoft.reqtrack.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "Complaint Data Transfer Object")
public class ComplaintDto extends BaseDto {

    @ApiModelProperty(value = "DESCRIPTION")
    private String description;
    @ApiModelProperty(value = "LOCATION")
    private String location;
    @ApiModelProperty(value = "USER")
    private UserDto userDto;
    @ApiModelProperty(value = "CATEGORY")
    private CategoryDto categoryDto;
    @ApiModelProperty(value = "COMPLAINT STATUS")
    private ComplaintStatusDto complaintStatusDto;
    @ApiModelProperty(value = "GALLERY")
    private List<ComplaintGalleryDto> complaintGalleries;
}
