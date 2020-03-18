package com.ketechsoft.reqtrack.services;

import com.ketechsoft.reqtrack.dtos.ComplaintGalleryDto;
import com.ketechsoft.reqtrack.models.ComplaintGallery;

import java.util.List;

public interface ComplaintGalleryService {
    void addGallery(long complaintId, List<ComplaintGalleryDto> complaintGalleryDtoList);
    List<ComplaintGallery> getAllByComplaintId(long complaintId);
}
