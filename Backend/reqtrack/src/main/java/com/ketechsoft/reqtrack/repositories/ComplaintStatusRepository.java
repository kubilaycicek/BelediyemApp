package com.ketechsoft.reqtrack.repositories;

import com.ketechsoft.reqtrack.models.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {
    ComplaintStatus findById(long id);
}
