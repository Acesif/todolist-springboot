package com.task.management.impl;

import com.task.management.entity.Task;
import com.task.management.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    public static Task task = new Task();
    public static Task newtask = new Task();
    @InjectMocks
    private TaskServiceImpl taskService;
    @Mock
    private TaskRepository taskRepository;

    @BeforeAll
    public static void init(){
        task.setId(1L);
        task.setTask("Service Impl");
        task.setDeadline("09-09-09");
        task.setPriority(5);

        newtask.setId(2L);
        newtask.setTask("New Service Impl");
        newtask.setDeadline("19-19-19");
        newtask.setPriority(2);
    }

    @Test
    @DisplayName("Testing findAllTask")
    public void findAllTaskTest(){
        List<Task> dummyList = new ArrayList<>();
        dummyList.add(task);
        dummyList.add(newtask);
        Mockito.when(taskRepository.findAll()).thenReturn(dummyList);
        List<Task> taskList = taskService.findAllTask();
        Assertions.assertEquals(2,taskList.size());
    }

    @Test
    @DisplayName("Testing Task Creation")
    public void createTaskTest(){
        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(task);
        Task createdTask = taskService.createTask(task);
        Assertions.assertEquals(task.getId(),createdTask.getId());
    }

    @Test
    @DisplayName("Testing Task Deletion by Id")
    public void deleteTaskTest(){
        String result = taskService.deleteTask(1L);
        Assertions.assertEquals("Deleted Task",result);
    }

    @Test
    @DisplayName("Testing All Task Deletion")
    public void deleteAllTest(){
        String result = taskService.deleteAllTask();
        Assertions.assertEquals("Deleted All",result);
    }

    @Test
    @DisplayName("Testing get Task by Id")
    public void getTaskByIdTest(){
        Optional<Task> response = Optional.ofNullable(newtask);
        Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(response);
        Task fetchedTask = taskService.findTaskById(1L);
        Assertions.assertEquals(2L,fetchedTask.getId());
    }

    @Test
    @DisplayName("Testing Task Update")
    public void updateTaskTest(){
        Optional<Task> response = Optional.ofNullable(newtask);
        Mockito.when(taskRepository.findById(Mockito.anyLong())).thenReturn(response);
        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(newtask);
        Task updatedTask = taskService.updateTask(newtask,1L);
        Assertions.assertEquals(newtask.getTask(),updatedTask.getTask());
        Assertions.assertEquals(newtask.getDeadline(),updatedTask.getDeadline());
        Assertions.assertEquals(newtask.getPriority(),updatedTask.getPriority());
    }

}
