package com.anupamchaubey.TaskManagerAPI.controller;

import com.anupamchaubey.TaskManagerAPI.config.JwtUtil;
import com.anupamchaubey.TaskManagerAPI.dto.LoginDTO;
import com.anupamchaubey.TaskManagerAPI.dto.RegisterDTO;
import com.anupamchaubey.TaskManagerAPI.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    public AuthController(AuthService authService,  AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO dto) {

        String response= authService.registerUser(dto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO dto){

        Authentication auth=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        String token=jwtUtil.generateToken(dto.getEmail());
        return ResponseEntity.ok(token);
    }
}

