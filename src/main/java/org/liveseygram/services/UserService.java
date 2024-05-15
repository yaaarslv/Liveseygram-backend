package org.liveseygram.services;

import org.liveseygram.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.liveseygram.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repo = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User entity) {
        return repo.save(entity);
    }

    public void deleteById(long id) {
        repo.deleteById(id);
    }

    public void deleteByEntity(User entity) {
        repo.delete(entity);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public User update(User entity) {
        return repo.save(entity);
    }

    public Optional<User> findById(long id) {
        return repo.findById(id);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public Optional<User> findFirstByEmail(String email) {
        return repo.findFirstByEmail(email);
    }

    public Optional<User> findFirstByUsername(String username) {
        return repo.findFirstByUsername(username);
    }

    public boolean isPasswordCorrect(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}