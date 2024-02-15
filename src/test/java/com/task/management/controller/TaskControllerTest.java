package com.task.management.controller;

import com.task.management.entity.Task;
import com.task.management.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @Test
    @DisplayName("Tests to get all tasks")
    public void getAllTaskTest(){
        List<Task> taskList = new ArrayList<>();
        Task t1 = new Task();
        t1.setTask("Test task1");
        t1.setDeadline("12-12-12");
        t1.setPriority(1);

        Task t2 = new Task();
        t2.setTask("Test task2");
        t2.setDeadline("13-13-13");
        t2.setPriority(2);

        Task t3 = new Task();
        t3.setTask("Test task3");
        t3.setDeadline("14-14-14");
        t3.setPriority(3);

        taskList.add(t1);
        taskList.add(t2);
        taskList.add(t3);

        Mockito.when(taskService.findAllTask()).thenReturn(taskList);
        ResponseEntity<List<Task>> responseEntity = taskController.getAllTasks();

        Assertions.assertEquals(taskList.size(),Objects.requireNonNull(responseEntity.getBody()).size());
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }

    @Test
    @DisplayName("Test to create a task")
    public void createTaskTest(){
        Task task = new Task();
        task.setTask("Test task");
        task.setDeadline("11-11-11");
        task.setPriority(3);

        Task createdTask = new Task();
        createdTask.setTask("Test task");

        Mockito.when(taskService.createTask(task)).thenReturn(createdTask);

        ResponseEntity<Task> responseEntity = taskController.createTask(task);
        Assertions.assertEquals(createdTask.getTask(),Objects.requireNonNull(responseEntity.getBody()).getTask());
        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
    }
    @Test
    @DisplayName("Test to get Task by Id")
    public void getTaskByIdTest(){
        Task task = new Task();
        task.setId(1L);
        task.setTask("ID");
        task.setDeadline("00-00-00");
        task.setPriority(3);

        Mockito.when(taskService.findTaskById(Mockito.anyLong())).thenReturn(task);

        ResponseEntity<Task> responseEntity = taskController.getTaskById(1L);
        Assertions.assertEquals(task.getId(),Objects.requireNonNull(responseEntity.getBody()).getId());
        Assertions.assertEquals(task.getTask(),responseEntity.getBody().getTask());
        Assertions.assertEquals(task.getDeadline(),responseEntity.getBody().getDeadline());
        Assertions.assertEquals(task.getPriority(),responseEntity.getBody().getPriority());
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Test to update Task with Id")
    public void updateTaskTest(){
        Task updatedTask = new Task();
        updatedTask.setTask("After Update");
        updatedTask.setDeadline("22-22-22");
        updatedTask.setPriority(2);

        Mockito.when(taskService.updateTask(Mockito.any(),Mockito.anyLong())).thenReturn(updatedTask);
        ResponseEntity<Task> responseEntity = taskController.updateTask(updatedTask,1L);
        Assertions.assertEquals(updatedTask.getTask(),Objects.requireNonNull(responseEntity.getBody()).getTask());
        Assertions.assertEquals(updatedTask.getDeadline(),responseEntity.getBody().getDeadline());
        Assertions.assertEquals(updatedTask.getPriority(),responseEntity.getBody().getPriority());
    }

    @Test
    @DisplayName("Test to Delete Task with Id")
    public void deleteTaskTest() {
        ResponseEntity<String> responseEntity = taskController.deleteTask(1L);
        Assertions.assertEquals("Task deleted successfully",responseEntity.getBody());
    }

    @Test
    @DisplayName("Test to Delete All Tasks")
    public void deleteAllTaskTest(){
        ResponseEntity<String> responseEntity = taskController.deleteAllTask();
        Assertions.assertEquals("Deleted all Tasks",responseEntity.getBody());
    }
}
