package com.ketechsoft.reqtrack.repositories;

import com.ketechsoft.reqtrack.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findById(long departmentId);
}
