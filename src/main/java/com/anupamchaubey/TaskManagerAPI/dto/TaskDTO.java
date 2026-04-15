package com.anupamchaubey.TaskManagerAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    @NotBlank
    @Size(min = 1, max = 30, message = "cannot exceed length 30, write description instead!")
    private String taskName;

    @Size(min=0, max=1000, message = "can't exceed length 1000")
    private String taskDescription;

    @NotBlank
    private LocalDateTime deadline;

    @NotBlank
    private String userEmail;
}
