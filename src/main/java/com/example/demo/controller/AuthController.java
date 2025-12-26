package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final JwtTokenProvider jwtTokenProvider;
    
    // Simple in-memory user storage (for testing)
    private final Map<String, String> users = new HashMap<>();
    
    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        
        // Pre-populate with test users
        users.put("admin@example.com", "admin123");
        users.put("compliance@example.com", "compliance123");
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if (users.containsKey(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        
        users.put(request.getEmail(), request.getPassword());
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("email", request.getEmail());
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        String storedPassword = users.get(request.getEmail());
        
        if (storedPassword != null && storedPassword.equals(request.getPassword())) {
            String token = jwtTokenProvider.generateToken(request.getEmail());
            
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("email", request.getEmail());
            
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.status(401).body("Invalid credentials");
    }
    
    // Simple DTO
    public static class AuthRequest {
        private String email;
        private String password;
        private String role;
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}