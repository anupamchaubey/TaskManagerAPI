package com.anupamchaubey.TaskManagerAPI.service;

import com.anupamchaubey.TaskManagerAPI.dto.TaskDTO;
import com.anupamchaubey.TaskManagerAPI.exceptions.NoTaskWithThisIdExistsException;
import com.anupamchaubey.TaskManagerAPI.mapper.TaskMapper;
import com.anupamchaubey.TaskManagerAPI.model.Task;
import com.anupamchaubey.TaskManagerAPI.model.User;
import com.anupamchaubey.TaskManagerAPI.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.anupamchaubey.TaskManagerAPI.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskMapper taskMapper;

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public TaskService(TaskMapper taskMapper, TaskRepository taskRepository,  UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO createTask(Long userId, TaskDTO dto){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new NoTaskWithThisIdExistsException("no user with this id exists");
        }
        Task task = taskMapper.dtoToTask(dto);
        task.setUser(user.get());
        Task savedTask = taskRepository.save(task);
        return taskMapper.taskToDTO(savedTask);
    }
    public List<TaskDTO> getUserTasks(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new NoTaskWithThisIdExistsException("no user with this id exists");
        }
        List<TaskDTO> ls= taskMapper.tasksToDTOs(taskRepository.findByUser(user.get()));
        return ls;
    }
    public TaskDTO updateTask(Long taskId, TaskDTO dto){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isEmpty()){
            throw new NoTaskWithThisIdExistsException("no task with this id exists");
        }
        Task taskToUpdate = task.get();
        taskToUpdate.setTaskName(dto.getTaskName());
        taskToUpdate.setTaskDescription(dto.getTaskDescription());
        taskToUpdate.setDeadline(dto.getDeadline());
        return taskMapper.taskToDTO(taskRepository.save(taskToUpdate));
    }
    public void deleteTask(Long taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isEmpty()){
            throw new NoTaskWithThisIdExistsException("no task with this id exists");
        }
        taskRepository.delete(task.get());
    }
}
