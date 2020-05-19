package com.ketechsoft.reqtrack.repositories;

import com.ketechsoft.reqtrack.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    Complaint findById(long id);
    List<Complaint> findAllByUserId(long userId);
}
