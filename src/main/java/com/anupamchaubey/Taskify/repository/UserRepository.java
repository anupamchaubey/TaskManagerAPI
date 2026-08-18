package com.anupamchaubey.Taskify.repository;

import com.anupamchaubey.Taskify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserId(Long userId);

    User findByEmail(String username);
}
