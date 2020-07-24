package com.xebia.treewalaproject.services.impl;

import com.xebia.treewalaproject.model.GratitideMessageCount;
import com.xebia.treewalaproject.repository.GratitudeCountRepo;
import com.xebia.treewalaproject.services.GratitudeCountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GratitudeCountServiceImpl implements GratitudeCountService {

    @Autowired
    private GratitudeCountRepo gratitudeCountRepo;

    @Override
    public List<GratitideMessageCount> findAllMessagesCount() {
        return gratitudeCountRepo.findAll();
    }

    @Override
    public boolean findByEmployeeName(String employeeName) {
        return gratitudeCountRepo.findByEmployeeName(employeeName);
    }

    @Override
    public Optional<GratitideMessageCount> findByEmployeeIdString(Long employeeId) {
        return gratitudeCountRepo.findById(employeeId);
    }
}
