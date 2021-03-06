package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;



import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SimplyEmailServiceTest {

    @InjectMocks
   private SimplyEmailService simplyEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail(){
        //Given
        Mail mail = new Mail("marekszymulakodilla@gmail.com","Test","Test message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        simplyEmailService.send(mail);

        //Then
      verify(javaMailSender, times(1)).send(mailMessage);
    }
    @Test
    public void shouldSendEmailToCC(){
        //Given
        Mail mail = new Mail("marekszymulakodilla@gmail.com","Test","Test message","somemail@.com");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCC());

        //When
        simplyEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

}