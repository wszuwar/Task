package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyTaskList() throws Exception{
        //given
        List<TaskDto> taskList = new ArrayList<>();
        when(dbService.getAllTasks()).thenReturn(new ArrayList<>());
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
        verify(taskMapper, times(1)).mapToTaskDtoList(anyList());
        verifyNoMoreInteractions(taskMapper);
        verify(dbService, times(1)).getAllTasks();
        verifyNoMoreInteractions(dbService);
        }
        @Test
    public void shouldFetchTaskList() throws Exception{

        //Given
            Task task = new Task(1L,"Task","Test_1");
            TaskDto taskDto = new TaskDto(1L,"Task","Test_1");
            List<Task> tasks = Arrays.asList(task);
            List<TaskDto> taskDtoList = Arrays.asList(taskDto);

            when(dbService.getAllTasks()).thenReturn(tasks);
            when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtoList);

            //When & Then
            mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(jsonPath("$",hasSize(1)))
                    .andExpect(jsonPath("$.[0].id",is(1)))
                    .andExpect(jsonPath("$.[0].title",is("Task")))
                    .andExpect(jsonPath("$.[0].content",is("Test_1")));
                    verify(dbService,times(1)).getAllTasks();
                    verifyNoMoreInteractions(dbService);
                    verify(taskMapper,times(1)).mapToTaskDtoList(tasks);
                    verifyNoMoreInteractions(taskMapper);
        }

        @Test
    public void shouldFetchTask() throws Exception {
        //given
            Task task = new Task(1L,"Task","Test_1");
            TaskDto taskDto = new TaskDto(1L,"Task","Test_1");

            when(dbService.getTask(1L)).thenReturn(Optional.of(task));
            when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

            //When & Then
            mockMvc.perform(get("/v1/task/getTask",1).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(200))
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.title",is("Task")))
                    .andExpect(jsonPath("$.content",is("Test_1")));
                    verify(dbService,times(1)).getTask(1L);
                    verifyNoMoreInteractions(dbService);
                    verify(taskMapper,times(1)).mapToTaskDto(task);
                    verifyNoMoreInteractions(taskMapper);
        }

}
