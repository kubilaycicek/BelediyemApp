package com.ketechsoft.reqtrack.controllers;

import com.ketechsoft.reqtrack.constants.Mappings;
import com.ketechsoft.reqtrack.dtos.ComplaintDto;
import com.ketechsoft.reqtrack.dtos.ComplaintUpdateDto;
import com.ketechsoft.reqtrack.models.Complaint;
import com.ketechsoft.reqtrack.services.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Mappings.COMPLAINT_PATH)
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping("/")
    public ResponseEntity<ComplaintDto> addComplaint(@RequestBody ComplaintDto complaintDto) throws IOException {
        return ResponseEntity.ok(complaintService.addComplaint(complaintDto));
    }

    @PutMapping("/updateStatusAndCategory")
    public ResponseEntity<ComplaintDto> updateStatus(@RequestBody ComplaintUpdateDto complaintUpdateDto) {
        return ResponseEntity.ok(complaintService.updateStatusAndCategory(complaintUpdateDto));
    }

    @GetMapping("/{complaintID}")
    public ResponseEntity<ComplaintDto> getComplaint(@PathVariable(name = "complaintID") long complaintID) {
        return ResponseEntity.ok(complaintService.getById(complaintID));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Complaint>> getAllComplaint() {
        return ResponseEntity.ok(complaintService.getAll());
    }

    @GetMapping("/list/department/{departmentId}")
    public ResponseEntity<List<Complaint>> getAllComplaintByDepartmentId(@PathVariable long departmentId) {
        return ResponseEntity.ok(complaintService.getAllByDepartmentId(departmentId));
    }

    @GetMapping("/list/user/{userId}")
    public ResponseEntity<List<Complaint>> getAllComplaintByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(complaintService.getAllByUserId(userId));
    }
}
