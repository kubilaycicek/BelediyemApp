package com.ketechsoft.reqtrack.converters;

import com.ketechsoft.reqtrack.dtos.ComplaintDto;
import com.ketechsoft.reqtrack.models.Complaint;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ComplaintConverterImpl implements ComplaintConverter {

    private final ModelMapper modelMapper;
    private final CategoryConverter categoryConverter;
    private final UserConverter userConverter;
    private final ComplaintStatusConverter complaintStatusConverter;


    private void addSkipFieldsForConvertToDto() {
        TypeMap<ComplaintDto, Complaint> typeMap = modelMapper.getTypeMap(ComplaintDto.class, Complaint.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<ComplaintDto, Complaint>() {
                @Override
                protected void configure() {
                    skip(destination.getUser());
                    skip(destination.getCategory());
                    skip(destination.getComplaintStatus());
                }
            });
        }
    }

    private void addSkipFieldsForConvertToDomain() {
        TypeMap<Complaint, ComplaintDto> typeMap = modelMapper.getTypeMap(Complaint.class, ComplaintDto.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Complaint, ComplaintDto>() {
                @Override
                protected void configure() {
                    skip(destination.getUserDto());
                    skip(destination.getCategoryDto());
                    skip(destination.getComplaintStatusDto());
                }
            });
        }
    }

    @Override
    public ComplaintDto convertToComplaintDto(Complaint complaint) {
        if (complaint == null)
            return null;

        addSkipFieldsForConvertToDto();

        ComplaintDto complaintDto = modelMapper.map(complaint, ComplaintDto.class);
        if (complaint.getCategory() != null)
            complaintDto.setCategoryDto(categoryConverter.convertToCategoryDto(complaint.getCategory()));
        if (complaint.getUser() != null)
            complaintDto.setUserDto(userConverter.convertToUserDto(complaint.getUser()));
        if (complaint.getComplaintStatus() != null)
            complaintDto.setComplaintStatusDto(complaintStatusConverter.convertToComplaintStatusDto(complaint.getComplaintStatus()));

        return complaintDto;
    }

    @Override
    public Complaint convertToComplaint(ComplaintDto complaintDto) {
        if (complaintDto == null)
            return null;

        addSkipFieldsForConvertToDomain();

        Complaint complaint = modelMapper.map(complaintDto, Complaint.class);
        if (complaintDto.getCategoryDto() != null)
            complaint.setCategory(categoryConverter.convertToCategory(complaintDto.getCategoryDto()));
        if (complaintDto.getUserDto() != null)
            complaint.setUser(userConverter.convertToUser(complaintDto.getUserDto()));
        if (complaintDto.getComplaintStatusDto() != null)
            complaint.setComplaintStatus(complaintStatusConverter.convertToComplaintStatus(complaintDto.getComplaintStatusDto()));

        return complaint;
    }
}
