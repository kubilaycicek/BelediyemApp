package com.ketechsoft.reqtrack.services.impl;

import com.ketechsoft.reqtrack.converters.ComplaintStatusConverter;
import com.ketechsoft.reqtrack.dtos.ComplaintStatusDto;
import com.ketechsoft.reqtrack.models.ComplaintStatus;
import com.ketechsoft.reqtrack.models.Department;
import com.ketechsoft.reqtrack.repositories.ComplaintStatusRepository;
import com.ketechsoft.reqtrack.services.ComplaintStatusService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ComplaintStatusServiceImpl implements ComplaintStatusService {

    private final ComplaintStatusRepository complaintStatusRepository;
    private final ComplaintStatusConverter complaintStatusConverter;

    @Override
    public ComplaintStatusDto addComplaintStatus(ComplaintStatusDto complaintStatusDto) {
        ComplaintStatus complaintStatus = complaintStatusConverter
                .convertToComplaintStatus(complaintStatusDto);
        return complaintStatusConverter
                .convertToComplaintStatusDto(complaintStatusRepository.save(complaintStatus));
    }

    @Override
    public ComplaintStatusDto updateCategory(ComplaintStatusDto complaintStatusDto) {

        ComplaintStatus complaintStatus = complaintStatusRepository
                .findById(complaintStatusDto.getId());
        if (complaintStatus == null)
            throw new IllegalArgumentException("Complaint Status does not exist ! ID : " + complaintStatusDto.getId());

        complaintStatus.setName(complaintStatusDto.getName());
        complaintStatus.setDescription(complaintStatusDto.getDescription());
        return complaintStatusConverter
                .convertToComplaintStatusDto(complaintStatusRepository.save(complaintStatus));
    }

    @Override
    public ComplaintStatusDto getById(long complaintStatusId) {
        return null;
    }

    @Override
    public List<ComplaintStatus> getAll() {
        ArrayList<ComplaintStatus> list = new ArrayList<>();
        complaintStatusRepository.findAll().iterator().forEachRemaining(status -> list.add(status));
        return list;
    }
}
