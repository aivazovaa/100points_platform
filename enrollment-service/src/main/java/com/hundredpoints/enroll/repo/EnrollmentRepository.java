package com.hundredpoints.enroll.repo;

import com.hundredpoints.enroll.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    List<Enrollment> findByUserId(UUID userId);
}
