package com.xebia.treewalaproject.repository;

import com.xebia.treewalaproject.model.GratitideMessageCount;
import com.xebia.treewalaproject.model.GratitudeMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GratitudeCountRepo extends JpaRepository<GratitideMessageCount, Long> {
    boolean findByEmployeeName(String employeeName);
}
