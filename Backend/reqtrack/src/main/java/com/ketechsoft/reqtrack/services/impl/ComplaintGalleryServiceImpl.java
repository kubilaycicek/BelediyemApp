package com.ketechsoft.reqtrack.services.impl;

import com.ketechsoft.reqtrack.dtos.ComplaintGalleryDto;
import com.ketechsoft.reqtrack.models.Complaint;
import com.ketechsoft.reqtrack.models.ComplaintGallery;
import com.ketechsoft.reqtrack.repositories.ComplaintGalleryRepository;
import com.ketechsoft.reqtrack.repositories.ComplaintRepository;
import com.ketechsoft.reqtrack.services.ComplaintGalleryService;
import com.ketechsoft.reqtrack.utils.ImageConverterUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ComplaintGalleryServiceImpl implements ComplaintGalleryService {

    private final ComplaintGalleryRepository complaintGalleryRepository;

    @Override
    public void addGallery(Complaint complaint, List<ComplaintGalleryDto> complaintGalleryDtoList)  {
        for (ComplaintGalleryDto complaintGalleryDto : complaintGalleryDtoList) {
            ComplaintGallery complaintGallery = new ComplaintGallery();
            complaintGallery.setImageUrl(complaintGalleryDto.getImageUrl());
            complaintGallery.setComplaint(complaint);
            complaintGalleryRepository.save(complaintGallery);
        }
    }

    @Override
    public List<ComplaintGallery> getAllByComplaintId(long complaintId) {
        ArrayList<ComplaintGallery> list = new ArrayList<>();
        complaintGalleryRepository.findAll().iterator().forEachRemaining(image -> list.add(image));
        return list;
    }
}
