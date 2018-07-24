package com.crud.tasks.trello.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

@RunWith(MockitoJUnitRunner.class)

public class DbServiceTestSuite {
    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {

        //Given
        Task task1 = new Task(1L, "test", "my_test");
        List<Task> taskList = Arrays.asList(task1);

        when(taskRepository.findAll()).thenReturn(taskList);

        //When
        List<Task> allTasks = dbService.getAllTasks();

        //Then
        Assert.assertEquals(1, allTasks.size());

    }

    @Test
    public void testFindByIdTask(){
        //Given
        Task task1 = new Task(1L, "test", "my_test");

        when(taskRepository.findOne(anyLong())).thenReturn(task1);

        //When
        Task result = dbService.getTaskById(1L);
        long id = result.getId();
        //Then
        Assert.assertEquals(1L,id);

    }
}