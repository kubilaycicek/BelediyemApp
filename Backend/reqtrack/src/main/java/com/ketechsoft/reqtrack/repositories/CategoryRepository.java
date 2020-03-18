package com.ketechsoft.reqtrack.repositories;

import com.ketechsoft.reqtrack.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
   Category findById(long categoryID);
}
