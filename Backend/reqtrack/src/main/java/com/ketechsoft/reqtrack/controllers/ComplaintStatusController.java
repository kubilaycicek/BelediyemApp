package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.ComplaintStatusDto;
import com.ketechsoft.reqtrack.models.ComplaintStatus;
import com.ketechsoft.reqtrack.services.ComplaintStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(Mappings.COMPLAINT_STATUS_PATH)
public class ComplaintStatusController {

    private final ComplaintStatusService complaintStatusService;

    @PostMapping("/")
    public ResponseEntity<ComplaintStatusDto> addCompaintStatus(@RequestBody ComplaintStatusDto complaintStatusDTO) {
        return ResponseEntity.ok(complaintStatusService.addComplaintStatus(complaintStatusDTO));
    }

    @PutMapping("/")
    public ResponseEntity<ComplaintStatusDto> updateComplaintStatus(@RequestBody ComplaintStatusDto complaintStatusDTO) {
        return ResponseEntity.ok(complaintStatusService.updateCategory(complaintStatusDTO));
    }

    @GetMapping("/{complaintStatusID}")
    public ResponseEntity<ComplaintStatusDto> getComplaintStatus(@PathVariable(name = "complaintStatusID") long complaintStatusID) {
        return ResponseEntity.ok(complaintStatusService.getById(complaintStatusID));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ComplaintStatus>> getAllComplaintStatus() {
        return ResponseEntity.ok(complaintStatusService.getAll());
    }
}
