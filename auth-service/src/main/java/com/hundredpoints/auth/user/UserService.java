package com.hundredpoints.auth.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) { this.repo = repo; }

    public UserEntity register(String email, String password) {
        if (repo.findByEmail(email).isPresent()) throw new RuntimeException("email_taken");
        UserEntity u = new UserEntity();
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(password));
        return repo.save(u);
    }

    public UserEntity authenticate(String email, String password) {
        UserEntity u = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("not_found"));
        if (!encoder.matches(password, u.getPasswordHash())) throw new RuntimeException("bad_credentials");
        return u;
    }
}
