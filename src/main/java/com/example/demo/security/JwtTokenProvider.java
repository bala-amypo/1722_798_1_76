// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import javax.crypto.SecretKey;
// import java.util.Date;

// @Component
// public class JwtTokenProvider {
    
//     private final SecretKey secretKey;
//     private final long validityInMs;
    
//     public JwtTokenProvider(String secret, long validityInMs) {
//         this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
//         this.validityInMs = validityInMs;
//     }
    
//     public String generateToken(UserPrincipal userPrincipal) {
//         Date now = new Date();
//         Date expiryDate = new Date(now.getTime() + validityInMs);
        
//         return Jwts.builder()
//                 .setSubject(userPrincipal.getUsername())
//                 .setIssuedAt(now)
//                 .setExpiration(expiryDate)
//                 .signWith(secretKey, SignatureAlgorithm.HS256)
//                 .compact();
//     }
    
//     public String getUsernameFromToken(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(secretKey)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }
    
//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder()
//                     .setSigningKey(secretKey)
//                     .build()
//                     .parseClaimsJws(token);
//             return true;
//         } catch (JwtException | IllegalArgumentException e) {
//             return false;
//         }
//     }
// }