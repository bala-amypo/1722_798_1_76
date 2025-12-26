// package com.example.demo.security;

// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.HashMap;
// import java.util.Map;

// @Service
// public class AppUserDetailsService implements UserDetailsService {
    
//     private final Map<String, String> users = new HashMap<>();
    
//     public AppUserDetailsService() {
//         // Pre-populate with test users
//         users.put("admin@example.com", "admin123");
//         users.put("compliance@example.com", "compliance123");
//         users.put("user@example.com", "password123");
//     }
    
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         if (users.containsKey(username)) {
//             return User.withUsername(username)
//                     .password("{noop}" + users.get(username))
//                     .authorities("USER")
//                     .build();
//         }
//         throw new UsernameNotFoundException("User not found: " + username);
//     }
    
//     public void registerUser(String email, String password) {
//         users.put(email, password);
//     }
// }

// // // package com.example.demo.security;

// // // import org.springframework.security.core.userdetails.User;
// // // import org.springframework.security.core.userdetails.UserDetails;
// // // import org.springframework.security.core.userdetails.UserDetailsService;
// // // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // // import org.springframework.stereotype.Service;

// // // import java.util.HashMap;
// // // import java.util.Map;

// // // @Service
// // // public class AppUserDetailsService implements UserDetailsService {
    
// // //     private final Map<String, String> users = new HashMap<>();
    
// // //     public AppUserDetailsService() {
// // //         // Pre-populate with test users
// // //         users.put("admin@example.com", "admin123");
// // //         users.put("compliance@example.com", "compliance123");
// // //         users.put("user@example.com", "password123");
// // //     }
    
// // //     @Override
// // //     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// // //         if (users.containsKey(username)) {
// // //             return User.withUsername(username)
// // //                     .password("{noop}" + users.get(username)) // {noop} means no password encoding
// // //                     .authorities("USER")
// // //                     .build();
// // //         }
// // //         throw new UsernameNotFoundException("User not found: " + username);
// // //     }
    
// // //     // Optional: Add user registration method
// // //     public void registerUser(String email, String password) {
// // //         users.put(email, password);
// // //     }
// // // }


// // package com.example.demo.security;

// // import org.springframework.security.core.userdetails.User;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.security.core.userdetails.UserDetailsService;
// // import org.springframework.security.core.userdetails.UsernameNotFoundException;
// // import org.springframework.stereotype.Service;

// // import java.util.HashMap;
// // import java.util.Map;

// // @Service
// // public class AppUserDetailsService implements UserDetailsService {
    
// //     private final Map<String, String> users = new HashMap<>();
    
// //     public AppUserDetailsService() {
// //         // Pre-populate with test users
// //         users.put("admin@example.com", "admin123");
// //         users.put("compliance@example.com", "compliance123");
// //         users.put("user@example.com", "password123");
// //     }
    
// //     @Override
// //     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// //         if (users.containsKey(username)) {
// //             return User.withUsername(username)
// //                     .password("{noop}" + users.get(username)) // {noop} means no password encoding
// //                     .authorities("USER")
// //                     .build();
// //         }
// //         throw new UsernameNotFoundException("User not found: " + username);
// //     }

// //     // Add this method for registration
// //     public void registerUser(String email, String password) {
// //         users.put(email, password);
// //     }
// // }




package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppUserDetailsService implements UserDetailsService {
    
    private final Map<String, String> users = new HashMap<>();
    
    public AppUserDetailsService() {
        // Pre-populate with test users
        users.put("admin@example.com", "admin123");
        users.put("compliance@example.com", "compliance123");
        users.put("user@example.com", "password123");
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (users.containsKey(username)) {
            return User.withUsername(username)
                    .password("{noop}" + users.get(username)) // {noop} means no password encoding
                    .authorities("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
    
    // Add this method for registration
    public void registerUser(String email, String password) {
        users.put(email, password);
    }
}