package com.anupamchaubey.Taskify.service;

import com.anupamchaubey.Taskify.dto.RegisterDTO;
import com.anupamchaubey.Taskify.exceptions.UserAlreadyExistsException;
import com.anupamchaubey.Taskify.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.anupamchaubey.Taskify.repository.*;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String registerUser(RegisterDTO dto){

        User user = userRepository.findByEmail(dto.getEmail());
        //check if email already exists
        if(user!=null){
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
}
