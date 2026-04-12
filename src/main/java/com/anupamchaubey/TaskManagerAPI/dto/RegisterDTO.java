package com.anupamchaubey.TaskManagerAPI.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "Name is Required")
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6, max=20, message= "Password must be between 6 and 20 characters")
    private String password;
}
