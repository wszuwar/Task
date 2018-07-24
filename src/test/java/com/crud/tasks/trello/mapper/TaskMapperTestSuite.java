package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

  @Autowired
  private TaskMapper taskMapper;



    @Test
    public void testMapTotask(){

        //Given
        TaskDto taskDto = new TaskDto(1L,"test","my_test");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertNotNull(task);
        assertEquals(1L,task.getId(),0);
        assertEquals("test",task.getTitle());
        assertEquals("my_test",task.getContent());
    }

    @Test
    public void testMapToTaskDto(){

        //Given
        Task task = new Task(1L,"test1","my1_test");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertNotNull(taskDto);
        assertEquals(1L,taskDto.getId(),0);
        assertEquals("test1",taskDto.getTitle());
        assertEquals("my1_test",taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList(){

        //Given
       Task task = new Task(1L,"test","my_test");
       List<Task> taskList = Arrays.asList(task);

       //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(taskDtoList);
        assertEquals(1,taskDtoList.size());
        taskDtoList.stream().forEach(
                taskDto -> {
                    assertEquals(1L,taskDto.getId(),0);
                    assertEquals("test",taskDto.getTitle());
                    assertEquals("my_test",taskDto.getContent());
                }
        );



    }
}
