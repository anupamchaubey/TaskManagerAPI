package com.anupamchaubey.Taskify.service;

import com.anupamchaubey.Taskify.dto.TaskDTO;
import com.anupamchaubey.Taskify.exceptions.NoTaskWithThisIdExistsException;
import com.anupamchaubey.Taskify.exceptions.NoUserWithGivenIdExistsException;
import com.anupamchaubey.Taskify.mapper.TaskMapper;
import com.anupamchaubey.Taskify.model.Task;
import com.anupamchaubey.Taskify.model.User;
import com.anupamchaubey.Taskify.repository.TaskRepository;
import com.anupamchaubey.Taskify.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(
            TaskMapper taskMapper,
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // CREATE TASK
    public TaskDTO createTask(String email, TaskDTO dto) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new NoUserWithGivenIdExistsException(
                    "No user with this email exists"
            );
        }

        Task task = taskMapper.dtoToTask(dto);

        // Associate task with logged-in user
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return taskMapper.taskToDTO(savedTask);
    }

    // GET ALL TASKS OF LOGGED-IN USER
    public List<TaskDTO> getUserTasks(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new NoUserWithGivenIdExistsException(
                    "No user with this email exists"
            );
        }

        List<Task> tasks = taskRepository.findByUser(user);

        return taskMapper.tasksToDTOs(tasks);
    }

    // UPDATE TASK
    public TaskDTO updateTask(Long taskId, TaskDTO dto) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new NoTaskWithThisIdExistsException(
                                "No task with this id exists"
                        )
                );

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new NoUserWithGivenIdExistsException(
                    "No authenticated user found"
            );
        }

        String currentUsername = auth.getName();

        // Make sure the task belongs to a user
        if (task.getUser() == null) {
            throw new NoUserWithGivenIdExistsException(
                    "This task is not associated with any user"
            );
        }

        // Make sure the logged-in user owns this task
        if (!currentUsername.equals(task.getUser().getEmail())) {
            throw new NoTaskWithThisIdExistsException(
                    "You are not authorized to update this task"
            );
        }

        // Update existing task instead of creating a new one
        task.setTaskName(dto.getTaskName());
        task.setTaskDescription(dto.getTaskDescription());
        task.setDeadline(dto.getDeadline());

        Task updatedTask = taskRepository.save(task);

        return taskMapper.taskToDTO(updatedTask);
    }

    // DELETE TASK
    public void deleteTask(Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new NoTaskWithThisIdExistsException(
                                "No task with this id exists"
                        )
                );

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new NoUserWithGivenIdExistsException(
                    "No authenticated user found"
            );
        }

        String currentUsername = auth.getName();

        // Make sure the task belongs to a user
        if (task.getUser() == null) {
            throw new NoUserWithGivenIdExistsException(
                    "This task is not associated with any user"
            );
        }

        // Make sure the logged-in user owns this task
        if (!currentUsername.equals(task.getUser().getEmail())) {
            throw new NoTaskWithThisIdExistsException(
                    "You are not authorized to delete this task"
            );
        }

        taskRepository.delete(task);
    }
}