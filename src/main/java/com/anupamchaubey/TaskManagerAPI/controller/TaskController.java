package com.anupamchaubey.TaskManagerAPI.controller;

import com.anupamchaubey.TaskManagerAPI.model.Task;
import com.anupamchaubey.TaskManagerAPI.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@RequestParam Long userId){
        List<Task> ls= taskService.getAllTasks(userId);
        return new ResponseEntity<>(ls, HttpStatus.OK);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId){
        Task task=taskService.findById(taskId);
        return ResponseEntity.ok(task);
    }

}
