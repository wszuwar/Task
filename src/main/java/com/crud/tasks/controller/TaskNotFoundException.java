package com.crud.tasks.controller;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException() {
        super("Id Not Found");
    }
}
