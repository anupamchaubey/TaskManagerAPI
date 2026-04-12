package com.anupamchaubey.TaskManagerAPI.model;

import com.anupamchaubey.TaskManagerAPI.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long taskId;

    @Column(nullable = false)
    private String taskName;

    @Column(length = 1000)
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus taskStatus=TaskStatus.PENDING;

    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", nullable=false)
    private User user;

    @Column(updatable=false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
