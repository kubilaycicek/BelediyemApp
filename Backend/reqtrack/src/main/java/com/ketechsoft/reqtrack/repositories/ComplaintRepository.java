package com.ketechsoft.reqtrack.repositories;

import com.ketechsoft.reqtrack.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    Complaint findById(long id);
    List<Complaint> findAllByUserId(long userId);
    @Query(
            value = "SELECT * FROM complaint comp LEFT  JOIN category c on comp.category_id=c.id " +
                    "LEFT JOIN department deparment on deparment.id=c.department_id WHERE deparment.id=?1",
            nativeQuery = true
    )
    List<Complaint> findAllByDepartmentIdNative(long id);
}
