package com.anupamchaubey.TaskManagerAPI.controller;

import com.anupamchaubey.TaskManagerAPI.dto.LoginDTO;
import com.anupamchaubey.TaskManagerAPI.dto.RegisterDTO;
import com.anupamchaubey.TaskManagerAPI.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO dto) {

        String response= authService.registerUser(dto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO dto){
        String response= authService.loginUser(dto);
        return ResponseEntity.ok(response);
    }
}

