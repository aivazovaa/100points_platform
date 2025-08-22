package com.hundredpoints.auth.user;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String passwordHash;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
