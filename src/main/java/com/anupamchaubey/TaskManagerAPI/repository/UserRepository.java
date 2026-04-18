package com.anupamchaubey.TaskManagerAPI.repository;

import com.anupamchaubey.TaskManagerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserId(Long userId);

    User findByEmail(String username);
}
