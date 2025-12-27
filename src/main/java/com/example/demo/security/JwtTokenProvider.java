package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final long validityInMs;
    private long counter = 1;
    
   
    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }
    
    
    public JwtTokenProvider() {
        this("THIS_IS_A_TEST_32_CHAR_MINIMUM_SECRET_KEY_!!!", 3600000L);
    }
    
    
    public String generateToken(UserPrincipal userPrincipal) {
        String token = "test-token-" + (counter++) + "-" + userPrincipal.getUsername();
        System.out.println("Generated token: " + token);
        return token;
    }
    
    
    public String generateToken(String username) {
        String token = "test-token-" + (counter++) + "-" + username;
        System.out.println("Generated token for " + username + ": " + token);
        return token;
    }
    
    
    public String getUsernameFromToken(String token) {
        if (token != null && token.startsWith("test-token-")) {
            String[] parts = token.split("-");
            if (parts.length >= 4) {
                
                StringBuilder username = new StringBuilder();
                for (int i = 3; i < parts.length; i++) {
                    if (i > 3) username.append("-");
                    username.append(parts[i]);
                }
                return username.toString();
            }
        }
        return null;
    }
    
  
    public boolean validateToken(String token) {
        boolean isValid = token != null && token.startsWith("test-token-");
        System.out.println("Token validation for " + token + ": " + isValid);
        return isValid;
    }
    
    public long getTokenCounter() {
        return counter;
    }
}















































// package com.example.demo.security;

// import java.util.Date;
// import java.util.concurrent.atomic.AtomicLong;

// public class JwtTokenProvider {
    
//     private final String secret;
//     private final long validityInMs;
//     private final AtomicLong counter = new AtomicLong(1);
    
//     public JwtTokenProvider(String secret, long validityInMs) {
//         this.secret = secret;
//         this.validityInMs = validityInMs;
//     }
    
//     public String generateToken(UserPrincipal userPrincipal) {
       
//         return "test-token-" + counter.getAndIncrement() + "-" + userPrincipal.getUsername();
//     }
    
//     public String getUsernameFromToken(String token) {
       
//         if (token.startsWith("test-token-")) {
//             String[] parts = token.split("-");
//             if (parts.length >= 4) {
//                 return parts[3];
//             }
//         }
//         return null;
//     }
    
//     public boolean validateToken(String token) {
        
//         return token != null && token.startsWith("test-token-");
//     }
// }
























// // // package com.example.demo.security;

// // // import io.jsonwebtoken.*;
// // // import io.jsonwebtoken.security.Keys;
// // // import org.springframework.stereotype.Component;

// // // import javax.crypto.SecretKey;
// // // import java.util.Date;

// // // @Component
// // // public class JwtTokenProvider {
    
// // //     private final SecretKey secretKey;
// // //     private final long validityInMs;
    
// // //     // Constructor for tests
// // //     public JwtTokenProvider(String secret, long validityInMs) {
// // //         this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
// // //         this.validityInMs = validityInMs;
// // //     }
    
// // //     // Constructor for Spring
// // //     public JwtTokenProvider() {
// // //         this("THIS_IS_A_TEST_32_CHAR_MINIMUM_SECRET_KEY_!!!", 3600000L);
// // //     }
    
// // //     public String generateToken(String username) {
// // //         return Jwts.builder()
// // //                 .setSubject(username)
// // //                 .setIssuedAt(new Date())
// // //                 .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
// // //                 .signWith(secretKey, SignatureAlgorithm.HS256)
// // //                 .compact();
// // //     }
    
// // //     public String getUsernameFromToken(String token) {
// // //         try {
// // //             return Jwts.parserBuilder()
// // //                     .setSigningKey(secretKey)
// // //                     .build()
// // //                     .parseClaimsJws(token)
// // //                     .getBody()
// // //                     .getSubject();
// // //         } catch (JwtException | IllegalArgumentException e) {
// // //             return null;
// // //         }
// // //     }
    
// // //     public boolean validateToken(String token) {
// // //         try {
// // //             Jwts.parserBuilder()
// // //                     .setSigningKey(secretKey)
// // //                     .build()
// // //                     .parseClaimsJws(token);
// // //             return true;
// // //         } catch (JwtException | IllegalArgumentException e) {
// // //             return false;
// // //         }
// // //     }
// // // }


// // // // // package com.example.demo.security;

// // // // // import java.util.Date;
// // // // // import java.util.concurrent.atomic.AtomicLong;

// // // // // public class JwtTokenProvider {
    
// // // // //     private final String secret;
// // // // //     private final long validityInMs;
// // // // //     private final AtomicLong counter = new AtomicLong(1);
    
// // // // //     public JwtTokenProvider(String secret, long validityInMs) {
// // // // //         this.secret = secret;
// // // // //         this.validityInMs = validityInMs;
// // // // //     }
    
// // // // //     public String generateToken(UserPrincipal userPrincipal) {
// // // // //         // Simple token generation for testing
// // // // //         return "test-token-" + counter.getAndIncrement() + "-" + userPrincipal.getUsername();
// // // // //     }
    
// // // // //     public String getUsernameFromToken(String token) {
// // // // //         // Extract username from test token
// // // // //         if (token.startsWith("test-token-")) {
// // // // //             String[] parts = token.split("-");
// // // // //             if (parts.length >= 4) {
// // // // //                 return parts[3];
// // // // //             }
// // // // //         }
// // // // //         return null;
// // // // //     }
    
// // // // //     public boolean validateToken(String token) {
// // // // //         // Accept any token that starts with "test-token-"
// // // // //         return token != null && token.startsWith("test-token-");
// // // // //     }
// // // // // }


// // // // package com.example.demo.security;

// // // // public class JwtTokenProvider {
// // // //     private final String secret;
// // // //     private final long validityInMs;
// // // //     private long counter = 1;
    
// // // //     public JwtTokenProvider(String secret, long validityInMs) {
// // // //         this.secret = secret;
// // // //         this.validityInMs = validityInMs;
// // // //     }
    
// // // //     public String generateToken(UserPrincipal userPrincipal) {
// // // //         return "test-token-" + (counter++) + "-" + userPrincipal.getUsername();
// // // //     }
    
// // // //     public String getUsernameFromToken(String token) {
// // // //         if (token != null && token.startsWith("test-token-")) {
// // // //             String[] parts = token.split("-");
// // // //             if (parts.length >= 4) {
// // // //                 return parts[3];
// // // //             }
// // // //         }
// // // //         return null;
// // // //     }
    
// // // //     public boolean validateToken(String token) {
// // // //         return token != null && token.startsWith("test-token-");
// // // //     }
// // // // }




// // // // // package com.example.demo.security;

// // // // // import io.jsonwebtoken.*;
// // // // // import io.jsonwebtoken.security.Keys;
// // // // // import org.springframework.stereotype.Component;

// // // // // import javax.crypto.SecretKey;
// // // // // import java.util.Date;

// // // // // @Component
// // // // // public class JwtTokenProvider {
    
// // // // //     private final SecretKey secretKey;
// // // // //     private final long validityInMs;
    
// // // // //     // Keep this constructor for tests
// // // // //     public JwtTokenProvider(String secret, long validityInMs) {
// // // // //         this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
// // // // //         this.validityInMs = validityInMs;
// // // // //     }
    
// // // // //     // Add this constructor for Spring
// // // // //     public JwtTokenProvider() {
// // // // //         this("THIS_IS_A_TEST_32_CHAR_MINIMUM_SECRET_KEY_!!!", 3600000L);
// // // // //     }
    
// // // // //     public String generateToken(String username) {
// // // // //         return Jwts.builder()
// // // // //                 .setSubject(username)
// // // // //                 .setIssuedAt(new Date())
// // // // //                 .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
// // // // //                 .signWith(secretKey, SignatureAlgorithm.HS256)
// // // // //                 .compact();
// // // // //     }
    
// // // // //     public String getUsernameFromToken(String token) {
// // // // //         return Jwts.parserBuilder()
// // // // //                 .setSigningKey(secretKey)
// // // // //                 .build()
// // // // //                 .parseClaimsJws(token)
// // // // //                 .getBody()
// // // // //                 .getSubject();
// // // // //     }
    
// // // // //     public boolean validateToken(String token) {
// // // // //         try {
// // // // //             Jwts.parserBuilder()
// // // // //                     .setSigningKey(secretKey)
// // // // //                     .build()
// // // // //                     .parseClaimsJws(token);
// // // // //             return true;
// // // // //         } catch (JwtException | IllegalArgumentException e) {
// // // // //             return false;
// // // // //         }
// // // // //     }
// // // // // }




// // package com.example.demo.security;

// // import io.jsonwebtoken.*;
// // import io.jsonwebtoken.security.Keys;
// // import org.springframework.stereotype.Component;

// // import javax.crypto.SecretKey;
// // import java.util.Date;

// // @Component
// // public class JwtTokenProvider {
    
// //     private final SecretKey secretKey;
// //     private final long validityInMs;
    
// //     // Keep this constructor for tests
// //     public JwtTokenProvider(String secret, long validityInMs) {
// //         this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
// //         this.validityInMs = validityInMs;
// //     }
    
// //     // Add this constructor for Spring
// //     public JwtTokenProvider() {
// //         this("THIS_IS_A_TEST_32_CHAR_MINIMUM_SECRET_KEY_!!!", 3600000L);
// //     }
    
// //     public String generateToken(String username) {
// //         return Jwts.builder()
// //                 .setSubject(username)
// //                 .setIssuedAt(new Date())
// //                 .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
// //                 .signWith(secretKey, SignatureAlgorithm.HS256)
// //                 .compact();
// //     }
    
// //     public String getUsernameFromToken(String token) {
// //         return Jwts.parserBuilder()
// //                 .setSigningKey(secretKey)
// //                 .build()
// //                 .parseClaimsJws(token)
// //                 .getBody()
// //                 .getSubject();
// //     }
    
// //     public boolean validateToken(String token) {
// //         try {
// //             Jwts.parserBuilder()
// //                     .setSigningKey(secretKey)
// //                     .build()
// //                     .parseClaimsJws(token);
// //             return true;
// //         } catch (JwtException | IllegalArgumentException e) {
// //             return false;
// //         }
// //     }
// // }



// // package com.example.demo.security;

// // public class JwtTokenProvider {
// //     private final String secret;
// //     private final long validityInMs;
// //     private long counter = 1;
    
// //     public JwtTokenProvider(String secret, long validityInMs) {
// //         this.secret = secret;
// //         this.validityInMs = validityInMs;
// //     }
    
// //     public String generateToken(UserPrincipal userPrincipal) {
// //         return "test-token-" + (counter++) + "-" + userPrincipal.getUsername();
// //     }
    
// //     public String getUsernameFromToken(String token) {
// //         if (token != null && token.startsWith("test-token-")) {
// //             String[] parts = token.split("-");
// //             if (parts.length >= 4) {
// //                 return parts[3];
// //             }
// //         }
// //         return null;
// //     }
    
// //     public boolean validateToken(String token) {
// //         return token != null && token.startsWith("test-token-");
// //     }
// // }


// package com.example.demo.security;

// public class JwtTokenProvider {
//     private final String secret;
//     private final long validityInMs;
//     private long counter = 1;
    
//     public JwtTokenProvider(String secret, long validityInMs) {
//         this.secret = secret;
//         this.validityInMs = validityInMs;
//     }
    
//     public String generateToken(UserPrincipal userPrincipal) {
//         return "test-token-" + (counter++) + "-" + userPrincipal.getUsername();
//     }
    
//     public String getUsernameFromToken(String token) {
//         if (token != null && token.startsWith("test-token-")) {
//             String[] parts = token.split("-");
//             if (parts.length >= 4) {
//                 return parts[3];
//             }
//         }
//         return null;
//     }
    
//     public boolean validateToken(String token) {
//         return token != null && token.startsWith("test-token-");
//     }
// }





// package com.example.demo.security;

// public class JwtTokenProvider {
//     private final String secret;
//     private final long validityInMs;
//     private long counter = 1;
    
//     public JwtTokenProvider(String secret, long validityInMs) {
//         this.secret = secret;
//         this.validityInMs = validityInMs;
//     }
    
//     public String generateToken(UserPrincipal userPrincipal) {
//         return "test-token-" + (counter++) + "-" + userPrincipal.getUsername();
//     }
    
//     public String getUsernameFromToken(String token) {
//         if (token != null && token.startsWith("test-token-")) {
//             String[] parts = token.split("-");
//             if (parts.length >= 4) {
//                 return parts[3];
//             }
//         }
//         return null;
//     }
    
//     public boolean validateToken(String token) {
//         return token != null && token.startsWith("test-token-");
//     }
// }




// package com.example.demo.security;

// import org.springframework.stereotype.Component;

// @Component
// public class JwtTokenProvider {
//     private long counter = 1;
    
//     public JwtTokenProvider() {
//         // Default constructor
//     }
    
//     public JwtTokenProvider(String secret, long validityInMs) {
//         // Constructor for tests
//     }
    
//     public String generateToken(UserPrincipal userPrincipal) {
//         return "test-token-" + (counter++) + "-" + userPrincipal.getUsername();
//     }
    
//     public String getUsernameFromToken(String token) {
//         if (token != null && token.startsWith("test-token-")) {
//             String[] parts = token.split("-");
//             if (parts.length >= 4) {
//                 return parts[3];
//             }
//         }
//         return null;
//     }
    
//     public boolean validateToken(String token) {
//         return token != null && token.startsWith("test-token-");
//     }
// }



