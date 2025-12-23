// CustomUserDetailsService.java
package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final Map<String, UserRecord> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public CustomUserDetailsService() {
        // Initialize with some test users
        register("admin@example.com", "password", "ADMIN");
        register("compliance@example.com", "password", "COMPLIANCE_OFFICER");
        register("hr@example.com", "password", "HR_MANAGER");
    }
    
    public UserRecord register(String email, String password, String role) {
        Long id = idCounter.getAndIncrement();
        UserRecord user = new UserRecord(id, email, password, role);
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