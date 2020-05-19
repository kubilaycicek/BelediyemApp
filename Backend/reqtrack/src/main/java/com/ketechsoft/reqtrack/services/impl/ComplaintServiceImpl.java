package com.ketechsoft.reqtrack.services.impl;

import com.ketechsoft.reqtrack.converters.CategoryConverter;
import com.ketechsoft.reqtrack.converters.ComplaintConverter;
import com.ketechsoft.reqtrack.converters.ComplaintStatusConverter;
import com.ketechsoft.reqtrack.converters.UserConverter;
import com.ketechsoft.reqtrack.dtos.ComplaintDto;
import com.ketechsoft.reqtrack.dtos.ComplaintGalleryDto;
import com.ketechsoft.reqtrack.models.*;
import com.ketechsoft.reqtrack.repositories.*;
import com.ketechsoft.reqtrack.services.ComplaintGalleryService;
import com.ketechsoft.reqtrack.services.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ComplaintStatusRepository complaintStatusRepository;
    private final ComplaintConverter complaintConverter;
    private final ComplaintGalleryService complaintGalleryService;


    @Override
    public ComplaintDto addComplaint(ComplaintDto complaintDto){

        Complaint complaint = complaintConverter.convertToComplaint(complaintDto);

        Category category = categoryRepository.findById(complaintDto.getCategoryDto().getId());
        if (category == null)
            throw new IllegalArgumentException("Category does not exist !");
        complaint.setCategory(category);

        ComplaintStatus complaintStatus = complaintStatusRepository
                .findById(complaintDto.getComplaintStatusDto().getId());
        if (complaintStatus == null)
            throw new IllegalArgumentException("Complaint Status does not exist !");
        complaint.setComplaintStatus(complaintStatus);

        User user = userRepository.findById(complaintDto.getUserDto().getId());
        if (user == null)
            throw new IllegalArgumentException("User does not exist !");
        complaint.setUser(user);

       ComplaintDto response = complaintConverter.convertToComplaintDto(complaintRepository.save(complaint));
       complaintGalleryService.addGallery(complaint, complaintDto.getComplaintGalleries());

        return response;
    }

    @Override
    public ComplaintDto updateStatus(ComplaintDto complaintDto) {
        Complaint complaint = complaintRepository.findById(complaintDto.getId());
        if (complaint != null) {
            ComplaintStatus complaintStatus = complaintStatusRepository
                    .findById(complaintDto.getComplaintStatusDto().getId());
            if (complaintStatus != null)
                complaint.setComplaintStatus(complaintStatus);
            else
                throw new IllegalArgumentException("Complaint Status does not exist !");
        } else
            throw new IllegalArgumentException("Complaint does not exist");

        return complaintConverter.convertToComplaintDto(complaintRepository.save(complaint));
    }

    @Override
    public ComplaintDto getById(long complaintID) {
        return complaintConverter.convertToComplaintDto(complaintRepository.findById(complaintID));
    }

    @Override
    public List<Complaint> getAll() {
        ArrayList<Complaint> list = new ArrayList<>();
        complaintRepository.findAll().iterator().forEachRemaining(complaint -> list.add(complaint));
        return list;
    }

    @Override
    public List<Complaint> getAllByUserId(long userId) {
        ArrayList<Complaint> list = new ArrayList<>();
        complaintRepository.findAllByUserId(userId).iterator().forEachRemaining(complaint -> list.add(complaint));
        return list;
    }
}
