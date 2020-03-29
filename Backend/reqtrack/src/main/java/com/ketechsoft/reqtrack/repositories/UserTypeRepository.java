package com.ketechsoft.reqtrack.repositories;

import com.ketechsoft.reqtrack.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long> {
    UserType findById(long userTypeId);
}
