// package com.example.demo.controller;

// import com.example.demo.security.CustomUserDetailsService;
// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.security.UserPrincipal;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;
// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {
    
//     private final AuthenticationManager authenticationManager;
//     private final JwtTokenProvider tokenProvider;
//     private final CustomUserDetailsService customUserDetailsService;
    
//     public AuthController(AuthenticationManager authenticationManager,
//                           JwtTokenProvider tokenProvider,
//                           CustomUserDetailsService customUserDetailsService) {
//         this.authenticationManager = authenticationManager;
//         this.tokenProvider = tokenProvider;
//         this.customUserDetailsService = customUserDetailsService;
//     }
    
//     @PostMapping("/login")
//     public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//         Authentication authentication = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(
//                 loginRequest.getEmail(),
//                 loginRequest.getPassword()
//             )
//         );
        
//         SecurityContextHolder.getContext().setAuthentication(authentication);
//         String jwt = tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());
        
//         Map<String, Object> response = new HashMap<>();
//         response.put("token", jwt);
//         response.put("email", loginRequest.getEmail());
        
//         return ResponseEntity.ok(response);
//     }
    
//     @PostMapping("/register")
//     public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
//         if (customUserDetailsService.getUserByEmail(registerRequest.getEmail()) != null) {
//             return ResponseEntity.badRequest().body("Email already in use");
//         }
        
//         var userRecord = customUserDetailsService.register(
//             registerRequest.getEmail(),
//             registerRequest.getPassword(),
//             registerRequest.getRole()
//         );
        
//         // Auto login after registration
//         Authentication authentication = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(
//                 registerRequest.getEmail(),
//                 registerRequest.getPassword()
//             )
//         );
        
//         SecurityContextHolder.getContext().setAuthentication(authentication);
//         String jwt = tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());
        
//         Map<String, Object> response = new HashMap<>();
//         response.put("message", "User registered successfully");
//         response.put("token", jwt);
//         response.put("user", Map.of(
//             "id", userRecord.getId(),
//             "email", userRecord.getEmail(),
//             "role", userRecord.getRole()
//         ));
        
//         return ResponseEntity.ok(response);
//     }
    
//     @GetMapping("/validate")
//     public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             return ResponseEntity.status(401).body("Invalid authorization header");
//         }
        
//         String token = authHeader.substring(7);
//         boolean isValid = tokenProvider.validateToken(token);
        
//         if (isValid) {
//             String username = tokenProvider.getUsernameFromToken(token);
//             Map<String, Object> response = new HashMap<>();
//             response.put("valid", true);
//             response.put("username", username);
//             return ResponseEntity.ok(response);
//         } else {
//             return ResponseEntity.status(401).body("Invalid token");
//         }
//     }
    
//     // Request DTOs
//     public static class LoginRequest {
//         private String email;
//         private String password;
        
//         public String getEmail() { return email; }
//         public void setEmail(String email) { this.email = email; }
        
//         public String getPassword() { return password; }
//         public void setPassword(String password) { this.password = password; }
//     }
    
//     public static class RegisterRequest {
//         private String email;
//         private String password;
//         private String role = "USER";
        
//         public String getEmail() { return email; }
//         public void setEmail(String email) { this.email = email; }
        
//         public String getPassword() { return password; }
//         public void setPassword(String password) { this.password = password; }
        
//         public String getRole() { return role; }
//         public void setRole(String role) { this.role = role; }
//     }
// }