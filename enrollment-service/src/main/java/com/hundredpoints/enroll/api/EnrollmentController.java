package com.hundredpoints.enroll.api;

import com.hundredpoints.enroll.model.Enrollment;
import com.hundredpoints.enroll.repo.EnrollmentRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enrollments")
@Validated
public class EnrollmentController {
    private final EnrollmentRepository repo;

    public EnrollmentController(EnrollmentRepository repo) { this.repo = repo; }

    public record EnrollRequest(@NotNull UUID userId, @NotNull UUID courseId) {}

    @PostMapping
    public Enrollment enroll(@RequestBody EnrollRequest req) {
        Enrollment e = new Enrollment();
        e.setUserId(req.userId());
        e.setCourseId(req.courseId());
        return repo.save(e);
    }

    @GetMapping("/user/{userId}")
    public List<Enrollment> byUser(@PathVariable UUID userId) {
        return repo.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health-ping")
    public Map<String, String> ping() { return Map.of("ok","true"); }
}
