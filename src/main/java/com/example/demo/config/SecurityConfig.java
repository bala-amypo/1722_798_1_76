package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Allow Swagger UI access
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/swagger-resources/**"
                ).permitAll()
                
                // Allow access to your API endpoints (adjust as needed)
                .requestMatchers("/api/persons").permitAll() // For testing
                .requestMatchers("/api/**").authenticated() // Protect other endpoints
                
                .anyRequest().authenticated()
            )
            // JWT configuration - remove this if you're not using JWT
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );
        
        return http.build();
    }

    // Add this method if you're using JWT
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        
        // Customize how JWT claims are converted to authorities
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // Extract roles/authorities from JWT claims
            // This depends on how your JWT is structured
            Collection<String> roles = jwt.getClaimAsStringList("roles");
            if (roles == null) {
                roles = Collections.emptyList();
            }
            
            return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        });
        
        return converter;
    }
}


// package com.example.demo.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
    
//     private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
//     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }
    
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll()
//                 .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
//                 .requestMatchers("/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             )
//             // Disable form login and basic auth to remove login page
//             .formLogin(form -> form.disable())
//             .httpBasic(basic -> basic.disable())
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
//         return http.build();
//     }
// }


// // package com.example.demo.security;

// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // import org.springframework.security.config.http.SessionCreationPolicy;
// // import org.springframework.security.web.SecurityFilterChain;
// // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// // @Configuration
// // @EnableWebSecurity
// // public class SecurityConfig {
    
// //     @Bean
// //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// //         http
// //             .csrf(csrf -> csrf.disable())
// //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// //             .authorizeHttpRequests(auth -> auth
// //                 .requestMatchers("/auth/**").permitAll()
// //                 .requestMatchers("/api/**").authenticated()
// //                 .anyRequest().permitAll()
// //             );
        
// //         return http.build();
// //     }
// // }



// // // package com.example.demo.security;

// // // import org.springframework.context.annotation.Bean;
// // // import org.springframework.context.annotation.Configuration;
// // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // // import org.springframework.security.config.http.SessionCreationPolicy;
// // // import org.springframework.security.web.SecurityFilterChain;

// // // @Configuration
// // // @EnableWebSecurity
// // // public class SecurityConfig {
    
// // //     @Bean
// // //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // //         http
// // //             .csrf(csrf -> csrf.disable())
// // //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// // //             .authorizeHttpRequests(auth -> auth
// // //                 .requestMatchers("/auth/**").permitAll()
// // //                 .requestMatchers("/api/**").authenticated()
// // //                 .anyRequest().permitAll()
// // //             );
        
// // //         return http.build();
// // //     }
// // // }