package com.hundredpoints.enroll.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="enrollments", uniqueConstraints = @UniqueConstraint(columnNames={"user_id","course_id"}))
public class Enrollment {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name="user_id", nullable=false)
    private UUID userId;
    @Column(name="course_id", nullable=false)
    private UUID courseId;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public UUID getCourseId() { return courseId; }
    public void setCourseId(UUID courseId) { this.courseId = courseId; }
}
