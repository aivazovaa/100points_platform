package com.hundredpoints.course.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable=false)
    private String title;
    private String description;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
