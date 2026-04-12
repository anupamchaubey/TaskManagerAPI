package com.anupamchaubey.TaskManagerAPI.service;

import com.anupamchaubey.TaskManagerAPI.dto.LoginDTO;
import com.anupamchaubey.TaskManagerAPI.dto.RegisterDTO;
import com.anupamchaubey.TaskManagerAPI.exception.InvalidCredentialsException;
import com.anupamchaubey.TaskManagerAPI.exception.UserAlreadyExistsException;
import com.anupamchaubey.TaskManagerAPI.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.anupamchaubey.TaskManagerAPI.repository.*;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder,  UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String registerUser(RegisterDTO dto){

        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        //check if email already exists
        if(user.isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }
        //create new user entity,
        // like we fill the user details by
        // extracting from dto and then
        // we will save it to the database
        User user1 = new User();
        user1.setName(dto.getName());
        user1.setEmail(dto.getEmail());

        //encode the password
        String encodedPassword=passwordEncoder.encode(dto.getPassword());
        user1.setPassword(encodedPassword);

        //save to database
        userRepository.save(user1);

        return "User registered successfully";
    }
    public String loginUser(LoginDTO dto){
        Optional<User> user = userRepository.findByEmail(dto.getEmail());

        if(user.isEmpty() || !passwordEncoder.matches(dto.getPassword(), user.get().getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        return "Access Granted";
    }

}
