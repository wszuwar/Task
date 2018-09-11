package com.crud.tasks.config;

import lombok.Getter;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@Getter
public class CompanyEmployeConfig {

    @Value("${employee.name}")
    private String employeeName;

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.email}")
    private String companyMail;
}
