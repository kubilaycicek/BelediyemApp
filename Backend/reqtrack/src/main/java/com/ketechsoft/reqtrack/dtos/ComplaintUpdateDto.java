package com.ketechsoft.reqtrack.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintUpdateDto {
    private long complaintId;
    private long statusId;
    private long categoryId;
}
