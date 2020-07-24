package com.xebia.treewalaproject.client;

import com.xebia.treewalaproject.model.EmployeeMetaData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="ZohoClientService", url="https://people.zoho.com/people/api/forms")
public interface ZohoClientService {
    @GetMapping(value = "/P_EmployeeView/records")
    List<EmployeeMetaData> getAllEmployee(@RequestParam(value="authtoken") String authtoken,
                                          @RequestParam(value="sIndex") int sIndex,
                                          @RequestParam(value="recLimit") int recLimit);
}
