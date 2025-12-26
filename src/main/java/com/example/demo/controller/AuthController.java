package com.example.demo.controller;

import com.example.demo.security.AppUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication APIs (No token required)")
public class AuthController {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final AppUserDetailsService userDetailsService;
    
    public AuthController(JwtTokenProvider jwtTokenProvider, AppUserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Email already exists")
    })
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            // In a real app, you'd check if user exists first
            userDetailsService.registerUser(request.getEmail(), request.getPassword());
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("email", request.getEmail());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticates user and returns JWT token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            
            // Check password (in memory, no encoding)
            if (userDetails.getPassword().equals("{noop}" + request.getPassword())) {
                String token = jwtTokenProvider.generateToken(request.getEmail());
                
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("email", request.getEmail());
                
                return ResponseEntity.ok(response);
            }
            
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body("User not found");
        }
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



// package com.example.demo.controller;

// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {
    
//     private final JwtTokenProvider jwtTokenProvider;
    
//     // Simple in-memory user storage (for testing)
//     private final Map<String, String> users = new HashMap<>();
    
//     public AuthController(JwtTokenProvider jwtTokenProvider) {
//         this.jwtTokenProvider = jwtTokenProvider;
        
//         // Pre-populate with test users
//         users.put("admin@example.com", "admin123");
//         users.put("compliance@example.com", "compliance123");
//     }
    
//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody AuthRequest request) {
//         if (users.containsKey(request.getEmail())) {
//             return ResponseEntity.badRequest().body("Email already exists");
//         }
        
//         users.put(request.getEmail(), request.getPassword());
        
//         Map<String, String> response = new HashMap<>();
//         response.put("message", "User registered successfully");
//         response.put("email", request.getEmail());
        
//         return ResponseEntity.ok(response);
//     }
    
//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody AuthRequest request) {
//         String storedPassword = users.get(request.getEmail());
        
//         if (storedPassword != null && storedPassword.equals(request.getPassword())) {
//             String token = jwtTokenProvider.generateToken(request.getEmail());
            
//             Map<String, String> response = new HashMap<>();
//             response.put("token", token);
//             response.put("email", request.getEmail());
            
//             return ResponseEntity.ok(response);
//         }
        
//         return ResponseEntity.status(401).body("Invalid credentials");
//     }
    
//     // Simple DTO
//     public static class AuthRequest {
//         private String email;
//         private String password;
//         private String role;
        
//         public String getEmail() { return email; }
//         public void setEmail(String email) { this.email = email; }
        
//         public String getPassword() { return password; }
//         public void setPassword(String password) { this.password = password; }
        
//         public String getRole() { return role; }
//         public void setRole(String role) { this.role = role; }
//     }
// }



// package com.example.demo.controller;

// import com.example.demo.security.AppUserDetailsService;
// import com.example.demo.security.JwtTokenProvider;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {
    
//     private final JwtTokenProvider jwtTokenProvider;
//     private final AppUserDetailsService userDetailsService;
    
//     public AuthController(JwtTokenProvider jwtTokenProvider, AppUserDetailsService userDetailsService) {
//         this.jwtTokenProvider = jwtTokenProvider;
//         this.userDetailsService = userDetailsService;
//     }
    
//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody AuthRequest request) {
//         try {
//             // In a real app, you'd check if user exists first
//             userDetailsService.registerUser(request.getEmail(), request.getPassword());
            
//             Map<String, String> response = new HashMap<>();
//             response.put("message", "User registered successfully");
//             response.put("email", request.getEmail());
            
//             return ResponseEntity.ok(response);
//         } catch (Exception e) {
//             return ResponseEntity.badRequest().body("Error: " + e.getMessage());
//         }
//     }
    
//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody AuthRequest request) {
//         try {
//             // Simple authentication check
//             var userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            
//             // Check password (in memory, no encoding)
//             if (userDetails.getPassword().equals("{noop}" + request.getPassword())) {
//                 String token = jwtTokenProvider.generateToken(request.getEmail());
                
//                 Map<String, String> response = new HashMap<>();
//                 response.put("token", token);
//                 response.put("email", request.getEmail());
                
//                 return ResponseEntity.ok(response);
//             }
            
//             return ResponseEntity.status(401).body("Invalid credentials");
//         } catch (UsernameNotFoundException e) {
//             return ResponseEntity.status(401).body("User not found");
//         }
//     }
    
//     // Simple DTO
//     public static class AuthRequest {
//         private String email;
//         private String password;
//         private String role;
        
//         public String getEmail() { return email; }
//         public void setEmail(String email) { this.email = email; }
        
//         public String getPassword() { return password; }
//         public void setPassword(String password) { this.password = password; }
        
//         public String getRole() { return role; }
//         public void setRole(String role) { this.role = role; }
//     }
// }


package com.example.demo.controller;

import com.example.demo.security.AppUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final AppUserDetailsService userDetailsService;
    
    public AuthController(JwtTokenProvider jwtTokenProvider, AppUserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        try {
            // In a real app, you'd check if user exists first
            userDetailsService.registerUser(request.getEmail(), request.getPassword());
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("email", request.getEmail());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            // Simple authentication check
            var userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            
            // Check password (in memory, no encoding)
            if (userDetails.getPassword().equals("{noop}" + request.getPassword())) {
                String token = jwtTokenProvider.generateToken(request.getEmail());
                
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("email", request.getEmail());
                
                return ResponseEntity.ok(response);
            }
            
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(401).body("User not found");
        }
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