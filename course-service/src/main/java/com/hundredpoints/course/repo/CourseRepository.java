package com.hundredpoints.course.repo;

import com.hundredpoints.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {}
