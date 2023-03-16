package com.example.databaseexam.Repository;

import com.example.databaseexam.Entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation,Long> {
}
