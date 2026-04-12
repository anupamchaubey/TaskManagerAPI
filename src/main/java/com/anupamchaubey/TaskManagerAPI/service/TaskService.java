package com.anupamchaubey.TaskManagerAPI.service;

import org.springframework.stereotype.Service;
import com.anupamchaubey.TaskManagerAPI.repository.TaskRepository;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    

}
