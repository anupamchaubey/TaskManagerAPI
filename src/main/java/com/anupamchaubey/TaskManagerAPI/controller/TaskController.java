package com.anupamchaubey.TaskManagerAPI.controller;

import com.anupamchaubey.TaskManagerAPI.dto.TaskDTO;
import com.anupamchaubey.TaskManagerAPI.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    // C R U D

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@AuthenticationPrincipal UserDetails userDetails, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(userDetails.getUsername(), taskDTO));
    }
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(@AuthenticationPrincipal UserDetails userDetails) {
        String identifier=userDetails.getUsername();
        return ResponseEntity.ok(taskService.getUserTasks(identifier));
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {

        return ResponseEntity.ok(taskService.updateTask(taskId, taskDTO));
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
