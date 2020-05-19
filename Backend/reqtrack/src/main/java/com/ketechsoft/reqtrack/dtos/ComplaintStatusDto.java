package com.ketechsoft.reqtrack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintStatusDto extends BaseDto {

    private String name;
    private String description;
}
