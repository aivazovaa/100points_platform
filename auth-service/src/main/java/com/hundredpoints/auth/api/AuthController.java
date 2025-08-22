package com.hundredpoints.auth.api;

import com.hundredpoints.auth.jwt.JwtProvider;
import com.hundredpoints.auth.user.UserEntity;
import com.hundredpoints.auth.user.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {
    private final UserService users;
    private final JwtProvider jwt;

    public AuthController(UserService users, JwtProvider jwt) {
        this.users = users;
        this.jwt = jwt;
    }

    public record AuthRequest(@Email String email, @NotBlank String password) {}

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        UserEntity u = users.register(req.email(), req.password());
        return ResponseEntity.ok(Map.of("id", u.getId(), "email", u.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        UserEntity u = users.authenticate(req.email(), req.password());
        String token = jwt.createToken(u.getId(), u.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
