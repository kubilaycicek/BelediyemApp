package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.ComplaintGalleryDto;
import com.ketechsoft.reqtrack.models.ComplaintGallery;

public interface ComplaintGalleryConverter {
    ComplaintGalleryDto convertToComplaintGalleryDto(ComplaintGallery complaintGallery);
    ComplaintGallery convertToComplaintGallery(ComplaintGalleryDto complaintGalleryDto);
}
