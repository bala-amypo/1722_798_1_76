package com.example.demo.security;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CustomUserDetailsService {
    
    private final Map<String, UserPrincipal> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public CustomUserDetailsService() {
        // Initialize with some test users
        register("u1@example.com", "pass1", "COMPLIANCE_OFFICER");
        register("u2@example.com", "pass2", "HR_MANAGER");
        register("u3@example.com", "p3", "ADMIN");
    }
    
    public UserPrincipal register(String email, String password, String role) {
        Long id = idCounter.getAndIncrement();
        UserPrincipal user = new UserPrincipal(id, email, password, role);
        users.put(email, user);
        return user;
    }
    
    public UserPrincipal loadUserByUsername(String username) {
        UserPrincipal user = users.get(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }
}