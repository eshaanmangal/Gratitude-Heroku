package com.xebia.treewalaproject.repository;

import com.xebia.treewalaproject.model.EmployeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZohoDataRepo extends JpaRepository<EmployeeResponse,Long> {
    Optional<EmployeeResponse> findByStaffEmailId(String emailid);
}
