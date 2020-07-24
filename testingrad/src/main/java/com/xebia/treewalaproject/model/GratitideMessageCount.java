package com.xebia.treewalaproject.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class GratitideMessageCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Employee_Name")
    private String employeeName;

    @Column(name = "Gratitide_Count")
    private Long gratitudeCount;

}
