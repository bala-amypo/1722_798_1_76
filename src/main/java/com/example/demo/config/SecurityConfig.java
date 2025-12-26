






































// // package com.example.demo.config;

// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;
// // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // import org.springframework.security.web.SecurityFilterChain;

// // @Configuration
// // @EnableWebSecurity
// // public class SecurityConfig {

// //     @Bean
// //     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// //         http
// //             .csrf(csrf -> csrf.disable())
// //             .authorizeHttpRequests(auth -> auth
// //                 // Public endpoints
// //                 .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
// //                 .requestMatchers("/api/**").permitAll()  // Allow all API calls
// //                 .anyRequest().authenticated()
// //             );
        
// //         return http.build();
// //     }
// // }

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


// // // // package com.example.demo.security;

// // // // import org.springframework.context.annotation.Bean;
// // // // import org.springframework.context.annotation.Configuration;
// // // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // // // import org.springframework.security.config.http.SessionCreationPolicy;
// // // // import org.springframework.security.web.SecurityFilterChain;
// // // // import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// // // // @Configuration
// // // // @EnableWebSecurity
// // // // public class SecurityConfig {
    
// // // //     @Bean
// // // //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // // //         http
// // // //             .csrf(csrf -> csrf.disable())
// // // //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// // // //             .authorizeHttpRequests(auth -> auth
// // // //                 .requestMatchers("/auth/**").permitAll()
// // // //                 .requestMatchers("/api/**").authenticated()
// // // //                 .anyRequest().permitAll()
// // // //             );
        
// // // //         return http.build();
// // // //     }
// // // // }



// // // // // package com.example.demo.security;

// // // // // import org.springframework.context.annotation.Bean;
// // // // // import org.springframework.context.annotation.Configuration;
// // // // // import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// // // // // import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// // // // // import org.springframework.security.config.http.SessionCreationPolicy;
// // // // // import org.springframework.security.web.SecurityFilterChain;

// // // // // @Configuration
// // // // // @EnableWebSecurity
// // // // // public class SecurityConfig {
    
// // // // //     @Bean
// // // // //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// // // // //         http
// // // // //             .csrf(csrf -> csrf.disable())
// // // // //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// // // // //             .authorizeHttpRequests(auth -> auth
// // // // //                 .requestMatchers("/auth/**").permitAll()
// // // // //                 .requestMatchers("/api/**").authenticated()
// // // // //                 .anyRequest().permitAll()
// // // // //             );
        
// // // // //         return http.build();
// // // // //     }
// // // // // }




// package com.example.demo.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {
    
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll()
//                 .requestMatchers("/api/**").authenticated()
//                 .anyRequest().permitAll()
//             );
        
//         return http.build();
//     }
// }