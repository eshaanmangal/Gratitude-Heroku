package com.xebia.treewalaproject.services;

import com.xebia.treewalaproject.model.EmployeeResponse;

import java.util.Optional;
import java.util.Set;

public interface GetZohoData {
    public Set<EmployeeResponse> getAllData();
    Optional<EmployeeResponse> loadNyUsername(String username);
}
