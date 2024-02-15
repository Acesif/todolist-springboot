package com.task.management.service;

import com.task.management.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {
    List<Task> findAllTask();
    Task createTask(Task newTask);
    Task findTaskById(Long Id);
    Task updateTask(Task updatedTask, Long Id);
    String deleteTask(Long Id);
    String deleteAllTask();
}
