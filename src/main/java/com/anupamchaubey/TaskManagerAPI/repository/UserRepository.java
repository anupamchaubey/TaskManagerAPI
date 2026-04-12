package com.anupamchaubey.TaskManagerAPI.repository;

import com.anupamchaubey.TaskManagerAPI.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(@NotBlank @Email(message = "Enter valid Email") String email);
}
