package com.ketechsoft.reqtrack.dtos;

import lombok.Data;

import javax.persistence.Lob;

@Data
public class ComplaintGalleryDto extends BaseDto {
    @Lob
    private String imageUrl;
}
