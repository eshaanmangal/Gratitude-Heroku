package com.xebia.treewalaproject.services.impl;

import com.xebia.treewalaproject.client.ZohoClientService;
import com.xebia.treewalaproject.model.EmployeeMetaData;
import com.xebia.treewalaproject.model.EmployeeResponse;
import com.xebia.treewalaproject.repository.ZohoDataRepo;
import com.xebia.treewalaproject.services.GetZohoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetZohoDataImpl implements GetZohoData {

    @Autowired
    private ZohoClientService zohoClientService;

    @Autowired
    private ZohoDataRepo zohoDataRepo;

    @Value(value = "${zoho.token}")
    private String token;

    //private HashMap<String, Boolean> dataByEmails;

    @Override
    public Set <EmployeeResponse> getAllData() {
        int sIndex=0;
        int recLimit=200;
        Set<EmployeeResponse> staffResponseList =new HashSet<EmployeeResponse>();
        Boolean isMoreData=true;
        while(isMoreData) {
            List<EmployeeMetaData> staffMetaDataList = zohoClientService.getAllEmployee(token,sIndex,recLimit);
            System.out.println(staffMetaDataList.size());
            if(staffMetaDataList.size() ==1 ){
                EmployeeMetaData staffMetaData=staffMetaDataList.get(0);
                if(staffMetaData.getEmployeeID() ==null ){
                    isMoreData=false;
                    continue;
                }
            }
            if(staffMetaDataList.size() > 0 ) {
                staffResponseList.addAll(EmployeeResponse.of(staffMetaDataList));
                sIndex=sIndex+recLimit;
            }else {
                isMoreData=false;
            }
        }
        zohoDataRepo.saveAll(staffResponseList);
        return staffResponseList;
    }

    @Override
    public Optional<EmployeeResponse> loadNyUsername(String username) {
        return zohoDataRepo.findByStaffEmailId(username);
    }
}
