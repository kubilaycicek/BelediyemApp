package com.ketechsoft.reqtrack.services;


import com.ketechsoft.reqtrack.dtos.ComplaintStatusDto;
import com.ketechsoft.reqtrack.models.ComplaintStatus;

import java.util.List;

public interface ComplaintStatusService {
    ComplaintStatusDto addComplaintStatus(ComplaintStatusDto complaintStatusDto);
    ComplaintStatusDto updateCategory(ComplaintStatusDto complaintStatusDto);
    ComplaintStatusDto getById(long complaintStatusId);
    List<ComplaintStatus> getAll();
}
