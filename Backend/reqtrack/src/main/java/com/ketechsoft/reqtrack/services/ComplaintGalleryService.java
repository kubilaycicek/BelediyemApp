package com.ketechsoft.reqtrack.services;

import com.ketechsoft.reqtrack.dtos.ComplaintGalleryDto;
import com.ketechsoft.reqtrack.models.Complaint;
import com.ketechsoft.reqtrack.models.ComplaintGallery;

import java.io.IOException;
import java.util.List;

public interface ComplaintGalleryService {
    void addGallery(Complaint complaint, List<ComplaintGalleryDto> complaintGalleryDtoList);
    List<ComplaintGallery> getAllByComplaintId(long complaintId);
}
