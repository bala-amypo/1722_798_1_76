// CustomUserDetailsService.java
package com.example.demo.security;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CustomUserDetailsService {
    
    private final Map<String, UserRecord> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public CustomUserDetailsService() {
        // Empty constructor - tests will call register()
    }
    
    public UserRecord register(String email, String password, String role) {
        Long id = idCounter.getAndIncrement();
        UserRecord user = new UserRecord(id, email, password, role);
        users.put(email, user);
        return user;
    }
    
    public UserPrincipal loadUserByUsername(String username) {
        UserRecord user = users.get(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
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