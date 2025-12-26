// package com.example.demo.security;

// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.atomic.AtomicLong;

// public class CustomUserDetailsService {
    
//     private final Map<String, UserPrincipal> users = new HashMap<>();
//     private final AtomicLong idCounter = new AtomicLong(1);
    
//     public CustomUserDetailsService() {
//         // Initialize with some test users
//         register("u1@example.com", "pass1", "COMPLIANCE_OFFICER");
//         register("u2@example.com", "pass2", "HR_MANAGER");
//         register("u3@example.com", "p3", "ADMIN");
//     }
    
//     public UserPrincipal register(String email, String password, String role) {
//         Long id = idCounter.getAndIncrement();
//         UserPrincipal user = new UserPrincipal(id, email, password, role);
//         users.put(email, user);
//         return user;
//     }
    
//     public UserPrincipal loadUserByUsername(String username) {
//         UserPrincipal user = users.get(username);
//         if (user == null) {
//             throw new RuntimeException("User not found");
//         }
//         return user;
//     }
// }




// package com.example.demo.security;

// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.atomic.AtomicLong;

// public class CustomUserDetailsService {
//     private final Map<String, UserPrincipal> users = new HashMap<>();
//     private final AtomicLong idCounter = new AtomicLong(1);
    
//     public CustomUserDetailsService() {}
    
//     public UserPrincipal register(String email, String password, String role) {
//         Long id = idCounter.getAndIncrement();
//         UserPrincipal user = new UserPrincipal(id, email, password, role);
//         users.put(email, user);
//         return user;
//     }
    
//     public UserPrincipal loadUserByUsername(String username) {
//         UserPrincipal user = users.get(username);
//         if (user == null) {
//             throw new RuntimeException("User not found");
//         }
//         return user;
//     }
// }


// package com.example.demo.security;

// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.atomic.AtomicLong;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {
    
//     private final Map<String, String> users = new HashMap<>();
//     private final AtomicLong idCounter = new AtomicLong(1);
    
//     public CustomUserDetailsService() {
//         // Initialize with test users (same as AuthController)
//         users.put("admin@example.com", "admin123");
//         users.put("compliance@example.com", "compliance123");
//         users.put("u1@example.com", "pass1");
//         users.put("u2@example.com", "pass2");
//         users.put("u3@example.com", "p3");
//     }
    
//     // Keep your existing methods for tests
//     public UserPrincipal register(String email, String password, String role) {
//         Long id = idCounter.getAndIncrement();
//         UserPrincipal user = new UserPrincipal(id, email, password, role);
//         users.put(email, password);
//         return user;
//     }
    
//     public UserPrincipal loadUserByUsername(String username) {
//         if (users.containsKey(username)) {
//             return new UserPrincipal(1L, username, users.get(username), "USER");
//         }
//         throw new RuntimeException("User not found");
//     }
    
//     // Add this method for Spring Security
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         if (users.containsKey(username)) {
//             return User.withUsername(username)
//                     .password("{noop}" + users.get(username)) // {noop} means no password encoding
//                     .authorities("USER")
//                     .build();
//         }
//         throw new UsernameNotFoundException("User not found");
//     }
// }



package com.example.demo.security;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CustomUserDetailsService {
    
    private final Map<String, UserPrincipal> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public CustomUserDetailsService() {
        // Initialize with test users
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