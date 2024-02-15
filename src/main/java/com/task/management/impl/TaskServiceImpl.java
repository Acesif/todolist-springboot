package com.task.management.impl;

import com.task.management.entity.Task;
import com.task.management.repository.TaskRepository;
import com.task.management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Task> findAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task newTask) {
        return taskRepository.save(newTask);
    }

    @Override
    public Task findTaskById(Long Id) {
        return taskRepository.findById(Id).get();
    }

    @Override
    public Task updateTask(Task updatedTask, Long Id) {
        Task task = taskRepository.findById(Id).get();
        task.setId(task.getId());
        task.setTask(updatedTask.getTask());
        task.setPriority(updatedTask.getPriority());
        task.setDeadline(updatedTask.getDeadline());
        return taskRepository.save(task);
    }

    @Override
    public String deleteTask(Long Id) {
        taskRepository.deleteById(Id);
        return "Deleted Task";
    }

    @Override
    public String deleteAllTask() {
        taskRepository.deleteAll();
        return "Deleted All";
    }
}
