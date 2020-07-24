package com.xebia.treewalaproject.controller;


import com.xebia.treewalaproject.model.EmployeeResponse;
import com.xebia.treewalaproject.services.GetZohoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/getAllEmployeeData")
@CrossOrigin(origins = "*")
public class GetEmployeeDataController {

    @Autowired
    private GetZohoData getZohoData;

    @GetMapping("/getData")
    public ResponseEntity<Set<EmployeeResponse>> getAllMessageList(){
        return ResponseEntity.ok().body(getZohoData.getAllData());
    }
}
