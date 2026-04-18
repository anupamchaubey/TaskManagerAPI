package com.anupamchaubey.TaskManagerAPI.config;

import com.anupamchaubey.TaskManagerAPI.exceptions.NoUserWithGivenIdExistsException;
import com.anupamchaubey.TaskManagerAPI.model.User;
import com.anupamchaubey.TaskManagerAPI.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws NoUserWithGivenIdExistsException{

        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
}
