package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.ComplaintDto;
import com.ketechsoft.reqtrack.models.Complaint;
import com.ketechsoft.reqtrack.services.ComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Mappings.COMPLAINT_PATH)
@Api(description = "Complaint APIs")
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping("/")
    @ApiOperation(value = "Create Operation", response = ComplaintDto.class)
    public ResponseEntity<ComplaintDto> addComplaint(@RequestBody ComplaintDto complaintDto) {
        return ResponseEntity.ok(complaintService.addComplaint(complaintDto));
    }

    @PutMapping("/")
    @ApiOperation(value = "Update Status Operation", response = ComplaintDto.class)
    public ResponseEntity<ComplaintDto> updateStatus(@RequestBody ComplaintDto complaintDTO) {
        return ResponseEntity.ok(complaintService.updateStatus(complaintDTO));
    }

    @GetMapping("/{complaintID}")
    @ApiOperation(value = "Get By Id Operation", response = ComplaintDto.class)
    public ResponseEntity<ComplaintDto> getComplaint(@PathVariable(name = "complaintID") long complaintID) {
        return ResponseEntity.ok(complaintService.getById(complaintID));
    }

    @GetMapping("/list")
    @ApiOperation(value = "Get All Operation", response = ComplaintDto.class)
    public ResponseEntity<List<Complaint>> getAllComplaint() {
        return ResponseEntity.ok(complaintService.getAll());
    }
}
