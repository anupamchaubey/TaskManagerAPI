package com.anupamchaubey.Taskify.mapper;

import com.anupamchaubey.Taskify.dto.TaskDTO;
import com.anupamchaubey.Taskify.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public TaskDTO taskToDTO(Task task){
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setTaskDescription(task.getTaskDescription());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setUserEmail(task.getUser().getEmail());
        taskDTO.setTaskId(task.getTaskId());
        return taskDTO;
    }
    public Task dtoToTask(TaskDTO taskDTO){
        Task task=new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setDeadline(taskDTO.getDeadline());
        return task;
    }
    public List<TaskDTO> tasksToDTOs(List<Task> tasks){
        List<TaskDTO> taskDTOs=new ArrayList<>();
        for(Task task:tasks){
            taskDTOs.add(taskToDTO(task));
        }
        return taskDTOs;
    }

}
