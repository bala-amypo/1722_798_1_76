package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final Map<String, UserPrincipal> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private final PasswordEncoder passwordEncoder;
    
    public CustomUserDetailsService() {
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }
            
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
    
    public UserPrincipal register(String email, String password, String role) {
        Long id = idCounter.getAndIncrement();
        UserPrincipal user = new UserPrincipal(id, email, passwordEncoder.encode(password), role);
        users.put(email, user);
        return user;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPrincipal user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}