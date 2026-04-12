package com.anupamchaubey.TaskManagerAPI.service;

import com.anupamchaubey.TaskManagerAPI.exception.InvalidCredentialsException;
import com.anupamchaubey.TaskManagerAPI.exception.NoTaskWithThisIdExistsException;
import com.anupamchaubey.TaskManagerAPI.model.Task;
import com.anupamchaubey.TaskManagerAPI.model.User;
import com.anupamchaubey.TaskManagerAPI.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.anupamchaubey.TaskManagerAPI.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,  UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks(Long userId){
        Optional<User> user=userRepository.findByUserId(userId);
        if(user==null){
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        return taskRepository.findByUser(user.get());
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(()-> new NoTaskWithThisIdExistsException("No Task Found with this Id"));
    }
}
