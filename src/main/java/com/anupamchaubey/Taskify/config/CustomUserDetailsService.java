package com.anupamchaubey.Taskify.config;

import com.anupamchaubey.Taskify.exceptions.NoUserWithGivenIdExistsException;
import com.anupamchaubey.Taskify.model.User;
import com.anupamchaubey.Taskify.repository.UserRepository;
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
