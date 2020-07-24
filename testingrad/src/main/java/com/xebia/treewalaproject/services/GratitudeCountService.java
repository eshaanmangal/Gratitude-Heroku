package com.xebia.treewalaproject.services;

import com.xebia.treewalaproject.model.GratitideMessageCount;

import java.util.List;
import java.util.Optional;

public interface GratitudeCountService {
    List<GratitideMessageCount> findAllMessagesCount();
    boolean findByEmployeeName(String employeeName);
    Optional<GratitideMessageCount> findByEmployeeIdString(Long employeeId);
}
