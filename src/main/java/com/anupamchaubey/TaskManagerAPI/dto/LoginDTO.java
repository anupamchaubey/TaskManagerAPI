package com.anupamchaubey.TaskManagerAPI.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Email is Required")
    @Email(message = "Enter valid Email")
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

}
