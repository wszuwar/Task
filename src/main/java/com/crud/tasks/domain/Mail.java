package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private String toCC;

    public Mail(  String mailTo, String subject, String message){
        this.mailTo = getMailTo();
        this.subject = getSubject();
        this.message = getMessage();
    }
}
