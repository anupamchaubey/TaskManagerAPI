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

    public TaskDTO createTask(String email, TaskDTO dto){
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new NoTaskWithThisIdExistsException("no user with this id exists");
        }
        Task task = taskMapper.dtoToTask(dto);
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        return taskMapper.taskToDTO(savedTask);
    }
    public List<TaskDTO> getUserTasks(String email) {
        User user= userRepository.findByEmail(email);
        if(user==null){
            throw new NoTaskWithThisIdExistsException("no user with this id exists");
        }
        List<TaskDTO> ls= taskMapper.tasksToDTOs(taskRepository.findByUser(user));
        return ls;
    }
    public TaskDTO updateTask(Long taskId, TaskDTO dto){

        Task task = taskRepository.findById(taskId).orElseThrow(()-> new NoTaskWithThisIdExistsException("no task with this id exists"));

        Task taskToUpdate = task;
        taskToUpdate.setTaskName(dto.getTaskName());
        taskToUpdate.setTaskDescription(dto.getTaskDescription());
        taskToUpdate.setDeadline(dto.getDeadline());
        return taskMapper.taskToDTO(taskRepository.save(taskToUpdate));
    }
    public void deleteTask(Long taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new NoTaskWithThisIdExistsException("no task with this id exists"));
        taskRepository.delete(task);
    }
}
