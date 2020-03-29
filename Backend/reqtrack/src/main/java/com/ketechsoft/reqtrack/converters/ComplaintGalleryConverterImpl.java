package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.ComplaintGalleryDto;
import com.ketechsoft.reqtrack.models.ComplaintGallery;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ComplaintGalleryConverterImpl implements ComplaintGalleryConverter {

    private final ModelMapper modelMapper;

    @Override
    public ComplaintGalleryDto convertToComplaintGalleryDto(ComplaintGallery complaintGallery) {
        if (complaintGallery == null)
            return null;
        return modelMapper.map(complaintGallery, ComplaintGalleryDto.class);
    }

    @Override
    public ComplaintGallery convertToComplaintGallery(ComplaintGalleryDto complaintGalleryDto) {
        if (complaintGalleryDto == null)
            return null;
        return modelMapper.map(complaintGalleryDto, ComplaintGallery.class);
    }
}
