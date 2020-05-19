package com.ketechsoft.reqtrack.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ComplaintDto extends BaseDto {

    private String description;
    private String location;
    private UserDto userDto;
    private CategoryDto categoryDto;
    private ComplaintStatusDto complaintStatusDto;
    private List<ComplaintGalleryDto> complaintGalleries = new ArrayList<>();
}
