package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimplyEmailService;
import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimplyEmailService simplyEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Task: Once a day email";

//    @Scheduled(cron = "0 0 10 * * *")
  //  @Scheduled(fixedDelay = 10000)
   // @Scheduled(cron = "0 0 0 25 12 ?")
    public void sendInformationEmail(){
        long size = taskRepository.count();
        String taskVariants = "task";
        if (size!=1){
            taskVariants += "s";
        }

        simplyEmailService.sendSheduled(new Mail(adminConfig.getAdminMail(),SUBJECT,"Currently in database got: " + size + taskVariants));
    }
}
