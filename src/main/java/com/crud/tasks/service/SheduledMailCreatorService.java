package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyEmployeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class SheduledMailCreatorService {

    @Autowired
    private CompanyEmployeConfig employeConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    List<String> bestAnimes = new ArrayList<>();

    public String buildSheduledEmail(String message){

        List<String> bestAnimes = new ArrayList<>();
        bestAnimes.add("Bleach");
        bestAnimes.add("Naruti");
        bestAnimes.add("Black Clover");
        bestAnimes.add("One Punch Man");


        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("anime_url","https://www1.gogoanime.sh/");
        context.setVariable("anime_button","My Favorite website");
        context.setVariable("name", employeConfig.getEmployeeName() );
        context.setVariable("company_name", employeConfig.getCompanyName());
        context.setVariable("company_mail",employeConfig.getCompanyMail());
        context.setVariable("enable_button", true);
        context.setVariable("is_anime_lover", true);
        context.setVariable("best_animes", bestAnimes);
        return templateEngine.process("mail/sheduled-mail",context);
    }
}
