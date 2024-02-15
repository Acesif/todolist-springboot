package com.task.management.controller;

import com.task.management.entity.Task;
import com.task.management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> taskList = taskService.findAllTask();
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task getTask = taskService.findTaskById(id);
        return new ResponseEntity<>(getTask,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task updatedTask, @PathVariable Long id){
        Task task = taskService.updateTask(updatedTask,id);
        return new ResponseEntity<>(task,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAllTask(){
        taskService.deleteAllTask();
        return new ResponseEntity<>("Deleted all Tasks",HttpStatus.OK);
    }
}
