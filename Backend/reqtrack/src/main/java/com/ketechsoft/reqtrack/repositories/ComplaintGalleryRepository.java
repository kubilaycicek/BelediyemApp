package com.ketechsoft.reqtrack.repositories;

import com.ketechsoft.reqtrack.models.ComplaintGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintGalleryRepository extends JpaRepository<ComplaintGallery, Long> {
    List<ComplaintGallery> findAllByComplaintId(long complaintId);
}
