package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.ComplaintStatusDto;
import com.ketechsoft.reqtrack.models.ComplaintStatus;

public interface ComplaintStatusConverter {
    ComplaintStatusDto convertToComplaintStatusDto(ComplaintStatus complaintStatus);
    ComplaintStatus convertToComplaintStatus(ComplaintStatusDto complaintStatusDto);
}
