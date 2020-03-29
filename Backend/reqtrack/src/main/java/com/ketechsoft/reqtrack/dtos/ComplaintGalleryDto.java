package com.ketechsoft.reqtrack.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Lob;

@Data
@ApiModel(value = "Complaint Gallery Data Transfer Object")
public class ComplaintGalleryDto extends BaseDto {
    @Lob
    @ApiModelProperty(value = "IMAGE_URL")
    private String imageUrl;
    @ApiModelProperty(value = "COMPLAINT")
    private ComplaintDto complaintDto;
}
