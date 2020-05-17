package com.ketechsoft.reqtrack.services;

import com.ketechsoft.reqtrack.dtos.ComplaintDto;
import com.ketechsoft.reqtrack.models.Complaint;

import java.io.IOException;
import java.util.List;

public interface ComplaintService {
    ComplaintDto addComplaint(ComplaintDto complaintDto) throws IOException;
    ComplaintDto updateStatus(ComplaintDto ComplaintDto);
    ComplaintDto getById(long complaintID);
    List<Complaint> getAll();
}
