package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyEmployeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateEngine.*;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyEmployeConfig employeConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){
        Context context = new org.thymeleaf.context.Context();
        context.setVariable("message",message);
        context.setVariable("tasks_url","http://localhost:8888/tasks_frontend");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("employee_name",employeConfig.getEmployeeName());
        context.setVariable("company_name", employeConfig.getCompanyName());
        context.setVariable("company_mail",employeConfig.getCompanyMail());

        return templateEngine.process("mail/created-trello-card-mail",context);
    }
}
