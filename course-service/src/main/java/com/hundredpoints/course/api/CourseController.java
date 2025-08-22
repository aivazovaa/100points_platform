package com.hundredpoints.course.api;

import com.hundredpoints.course.model.Course;
import com.hundredpoints.course.repo.CourseRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courses")
@Validated
public class CourseController {
    private final CourseRepository repo;

    public CourseController(CourseRepository repo) { this.repo = repo; }

    public record CourseRequest(@NotBlank String title, String description) {}

    @GetMapping
    public List<Course> list() { return repo.findAll(); }

    @PostMapping
    public Course create(@RequestBody CourseRequest req) {
        Course c = new Course();
        c.setTitle(req.title());
        c.setDescription(req.description());
        return repo.save(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable UUID id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable UUID id, @RequestBody CourseRequest req) {
        return repo.findById(id).map(c -> {
            c.setTitle(req.title());
            c.setDescription(req.description());
            return ResponseEntity.ok(repo.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
