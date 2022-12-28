package com.example.agentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "firstName", column = @Column(name = "firstname")),
        @AttributeOverride(name = "lastName", column = @Column(name = "lastname")),
        @AttributeOverride(name = "username", column = @Column(nullable = false)),
        @AttributeOverride(name = "password", column = @Column(nullable = false)),
        @AttributeOverride(name = "createdAt", column = @Column),
        @AttributeOverride(name = "updatedAt", column = @Column),
        @AttributeOverride(name="isPasswordChanged", column = @Column)
})
@Data
public class Agent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void setCreationDateTime() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void setChangeDateTime() {
        this.updatedAt = LocalDateTime.now();
    }
}
