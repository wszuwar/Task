package com.crud.tasks.controller;

import com.crud.tasks.com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.GET, value = "taskId")
    public TaskDto getTask(Long taskId){
        return new TaskDto(1L,"test title","test_content");
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(){

    }
    @RequestMapping(method = RequestMethod.PUT, value = "upgradeTask")
    public TaskDto upgradeTask(TaskDto task){
        return new TaskDto(1L,"Edited test title","test+content");
    }
    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(TaskDto task){

    }


}
