package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final Map<String, UserRecord> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public CustomUserDetailsService() {
        // Initialize with some test users
        register("admin@example.com", "password", "ADMIN");
        register("compliance@example.com", "password", "COMPLIANCE_OFFICER");
        register("hr@example.com", "password", "HR_MANAGER");
        register("u1@example.com", "pass1", "COMPLIANCE_OFFICER");
        register("u2@example.com", "pass2", "HR_MANAGER");
        register("u3@example.com", "p3", "ADMIN");
    }
    
    public UserRecord register(String email, String password, String role) {
        // Check if user already exists
        if (users.containsKey(email)) {
            return users.get(email);
        }
        
        Long id = idCounter.getAndIncrement();
        String encodedPassword = passwordEncoder.encode(password);
        UserRecord user = new UserRecord(id, email, encodedPassword, role);
        users.put(email, user);
        return user;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRecord user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserPrincipal(user.id, user.email, user.password, user.role);
    }
    
    public UserRecord getUserByEmail(String email) {
        return users.get(email);
    }
    
    public static class UserRecord {
        private Long id;
        private String email;
        private String password;
        private String role;
        
        public UserRecord(Long id, String email, String password, String role) {
            this.id = id;
            this.email = email;
            this.password = password;
            this.role = role;
        }
        
        public Long getId() { return id; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
    }
}