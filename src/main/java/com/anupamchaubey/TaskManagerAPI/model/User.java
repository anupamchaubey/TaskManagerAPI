package com.anupamchaubey.TaskManagerAPI.model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.NonNull;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
