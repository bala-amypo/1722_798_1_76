package com.example.demo.security;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class JwtTokenProvider {
    
    private final String secret;
    private final long validityInMs;
    private final AtomicLong counter = new AtomicLong(1);
    
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }
    
    public String generateToken(UserPrincipal userPrincipal) {
        // Simple token generation for testing
        return "test-token-" + counter.getAndIncrement() + "-" + userPrincipal.getUsername();
    }
    
    public String getUsernameFromToken(String token) {
        // Extract username from test token
        if (token.startsWith("test-token-")) {
            String[] parts = token.split("-");
            if (parts.length >= 4) {
                return parts[3];
            }
        }
        return null;
    }
    
    public boolean validateToken(String token) {
        // Accept any token that starts with "test-token-"
        return token != null && token.startsWith("test-token-");
    }
}