package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.ComplaintStatusDto;
import com.ketechsoft.reqtrack.models.ComplaintStatus;
import com.ketechsoft.reqtrack.services.ComplaintStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Mappings.COMPLAINT_STATUS_PATH)
@Api(description = "Complaint Status APIs")
public class ComplaintStatusController {

    private final ComplaintStatusService complaintStatusService;

    @PostMapping("/")
    @ApiOperation(value = "Create Operation", response = ComplaintStatusDto.class)
    public ResponseEntity<ComplaintStatusDto> addCompaintStatus(@RequestBody ComplaintStatusDto complaintStatusDTO) {
        return ResponseEntity.ok(complaintStatusService.addComplaintStatus(complaintStatusDTO));
    }

    @PutMapping("/")
    @ApiOperation(value = "Update Operation", response = ComplaintStatusDto.class)
    public ResponseEntity<ComplaintStatusDto> updateComplaintStatus(@RequestBody ComplaintStatusDto complaintStatusDTO) {
        return ResponseEntity.ok(complaintStatusService.updateCategory(complaintStatusDTO));
    }

    @GetMapping("/{complaintStatusID}")
    @ApiOperation(value = "Get By Id Operation", response = ComplaintStatus.class)
    public ResponseEntity<ComplaintStatusDto> getComplaintStatus(@PathVariable(name = "complaintStatusID") long complaintStatusID) {
        return ResponseEntity.ok(complaintStatusService.getById(complaintStatusID));
    }

    @GetMapping("/list")
    @ApiOperation(value = "Get All Complaint Status", response = ComplaintStatus.class)
    public ResponseEntity<List<ComplaintStatus>> getAllComplaintStatus() {
        return ResponseEntity.ok(complaintStatusService.getAll());
    }
}
