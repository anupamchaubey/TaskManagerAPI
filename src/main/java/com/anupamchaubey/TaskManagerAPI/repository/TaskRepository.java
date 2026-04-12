package com.anupamchaubey.TaskManagerAPI.repository;

import com.anupamchaubey.TaskManagerAPI.model.Task;
import com.anupamchaubey.TaskManagerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
