package com.anupamchaubey.TaskManagerAPI.service;

import com.anupamchaubey.TaskManagerAPI.dto.LoginDTO;
import com.anupamchaubey.TaskManagerAPI.dto.RegisterDTO;
import com.anupamchaubey.TaskManagerAPI.exception.InvalidCredentialsException;
import com.anupamchaubey.TaskManagerAPI.exception.UserAlreadyExistsException;
import com.anupamchaubey.TaskManagerAPI.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.anupamchaubey.TaskManagerAPI.repository.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder,  UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String registerUser(RegisterDTO dto){

        User user = userRepository.findByEmail(dto.getEmail());
        //check if email already exists
        if(user != null){
            throw new UserAlreadyExistsException("User already exists");
        }
        //create new user entity,
        // like we fill the user details by
        // extracting from dto and then
        // we will save it to the database
        user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        //encode the password
        String encodedPassword=passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);

        //save to database
        userRepository.save(user);

        return "User registered successfully";
    }
    public String loginUser(LoginDTO dto){
        User user = userRepository.findByEmail(dto.getEmail());

        if(user==null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        return "Access Granted";
    }

}
