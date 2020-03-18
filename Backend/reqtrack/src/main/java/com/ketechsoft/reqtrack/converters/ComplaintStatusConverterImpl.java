package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.ComplaintDto;
import com.ketechsoft.reqtrack.dtos.ComplaintStatusDto;
import com.ketechsoft.reqtrack.models.ComplaintStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ComplaintStatusConverterImpl implements ComplaintStatusConverter {

    private final ModelMapper modelMapper;

    @Override
    public ComplaintStatusDto convertToComplaintStatusDto(ComplaintStatus complaintStatus) {
        if (complaintStatus == null)
            return null;

        return modelMapper.map(complaintStatus, ComplaintStatusDto.class);
    }

    @Override
    public ComplaintStatus convertToComplaintStatus(ComplaintStatusDto complaintStatusDto) {
        if (complaintStatusDto == null)
            return null;

        return modelMapper.map(complaintStatusDto, ComplaintStatus.class);
    }
}
