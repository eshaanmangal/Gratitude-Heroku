package com.xebia.treewalaproject.model;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@ToString
@Entity
public class EmployeeResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EmployeeID")
    private String staffID;

    @Column(name = "Employee_Name")
    private String staffName;

    @Column(name = "Employee_EmailId")
    private String staffEmailId;

    @Column(name = "Department")
    private String departmentName;

    @Column(name = "Location")
    private String location;

    public static Set<EmployeeResponse> of(List<EmployeeMetaData> staffMetaDataList) {
        Set<EmployeeResponse> staffResponseList=new HashSet<>();
        for (EmployeeMetaData staffMetaData : staffMetaDataList) {
            EmployeeResponse staffResponse = new EmployeeResponse();
            staffResponse.setStaffEmailId(staffMetaData.getXebiaEmailId());
            staffResponse.setDepartmentName(staffMetaData.getDepartment());
            staffResponse.setStaffID(staffMetaData.getEmployeeID());
            staffResponse.setStaffName(staffMetaData.getFullName());
            staffResponse.setLocation(staffMetaData.getLocation());
            staffResponseList.add(staffResponse);

        }
        return staffResponseList;
    }
}

