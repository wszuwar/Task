package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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

    @MockBean
    private Task task;

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
            mockMvc.perform(get("/v1/task/getTask/").contentType(MediaType.APPLICATION_JSON).param(
                    "taskId","1")
            )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.title",is("Task")))
                    .andExpect(jsonPath("$.content",is("Test_1")));
                    verify(dbService,times(1)).getTask(1L);
                    verifyNoMoreInteractions(dbService);
                    verify(taskMapper,times(1)).mapToTaskDto(task);
                    verifyNoMoreInteractions(taskMapper);
        }

        @Test
    public void shouldDeleteTask() throws Exception {
            //given
            Task task = new Task(1L,"Task","Test_1");


            doNothing().when(dbService).deleteTask(task.getId());

            mockMvc.perform(delete("/v1/task/deleteTask")
                    .param("taskId","1"))
                    .andExpect(status().isOk());
            verify(dbService,times(1)).deleteTask(task.getId());
            verifyNoMoreInteractions(dbService);

        }
        @Test
    public void shouldUpdatetask() throws Exception {
            //given
            Task task = new Task(1L,"Task","Test_1");
            TaskDto taskDto = new TaskDto(1L,"Task","Test_1");


            when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
            when(dbService.saveTask(any(Task.class))).thenReturn(task);
            when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

            Gson gson = new Gson();
            String jsonContent = gson.toJson(taskDto);

            //When & Then
            mockMvc.perform(put("/v1/task/upgradeTask",1L).contentType(MediaType.APPLICATION_JSON)
                            .param("taskId","1")
                    .characterEncoding("UTF-8")
                    .content(jsonContent)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.title",is("Task")))
                    .andExpect(jsonPath("$.content",is("Test_1")));
            verify(taskMapper,times(1)).mapToTaskDto(any(Task.class));
            verify(taskMapper,times(1)).mapToTask(any(TaskDto.class));
            verify(dbService,times(1)).saveTask(task);
            verifyNoMoreInteractions(taskMapper);
            verifyNoMoreInteractions(dbService);
        }
        @Test
    public void shouldCreateTask() throws Exception{

            //given
            Task task = new Task(1L,"Task","Test_1");
            TaskDto taskDto = new TaskDto(1L,"Task","Test_1");


            when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
            when(dbService.saveTask(task)).thenReturn(task);


            Gson gson = new Gson();
            String jsonContent = gson.toJson(taskDto);

            //When & Then
            mockMvc.perform(post("/v1/task/createTask/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("taskId","1")
                    .characterEncoding(("UTF-8"))
                    .content(jsonContent))
                    .andExpect(status().is(200));
            verify(dbService,times(1)).saveTask(task);
            verifyNoMoreInteractions(dbService);


        }


}
