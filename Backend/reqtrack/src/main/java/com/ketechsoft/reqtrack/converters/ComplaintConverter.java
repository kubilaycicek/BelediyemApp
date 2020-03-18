package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.ComplaintDto;
import com.ketechsoft.reqtrack.models.Complaint;

public interface ComplaintConverter {
    ComplaintDto convertToComplaintDto(Complaint complaint);
    Complaint convertToComplaint(ComplaintDto complaintDto);
}
