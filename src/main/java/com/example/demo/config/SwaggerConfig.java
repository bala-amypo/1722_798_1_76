// package com.example.demo.config;

// import io.swagger.v3.oas.annotations.OpenAPIDefinition;
// import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.security.SecurityScheme;
// import io.swagger.v3.oas.annotations.servers.Server;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// @OpenAPIDefinition(
//     info = @Info(
//         title = "COI Engine API",
//         version = "1.0",
//         description = "Conflict of Interest Engine Backend API"
//     ),
//     servers = {
//         @Server(
//             url = "https://9252.pro604cr.amypo.ai",
//             description = "Production Server"
//         ),
//         @Server(
//             url = "http://localhost:8080",
//             description = "Local Development Server"
//         )
//     },
//     security = @SecurityRequirement(name = "bearerAuth")
// )
// @SecurityScheme(
//     name = "bearerAuth",
//     type = SecuritySchemeType.HTTP,
//     scheme = "bearer",
//     bearerFormat = "JWT"
// )
// public class SwaggerConfig {
// }


package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // You need to change the port as per your server
                .servers(List.of(
                        new Server().url("https://9252.pro604cr.amypo.ai/
                ));
        }
}

// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Contact;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.info.License;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("COI Engine API")
//                         .version("1.0.0")
//                         .description("API for managing events, seat inventory, and dynamic pricing")
//                         .contact(new Contact()
//                                 .name("API Support")
//                                 .email("support@example.com"))
//                         .license(new License()
//                                 .name("Apache 2.0")
//                                 .url("http://springdoc.org")))
//                 .servers(List.of(
//                         new Server().url("https://9252.pro604cr.amypo.ai/").description("Production Server"),
//                         new Server().url("http://localhost:8080").description("Local Server")
//                 ))
//                 // Add global security requirement
//                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                 // Define the security scheme
//                 .components(new io.swagger.v3.oas.models.Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new SecurityScheme()
//                                         .name("bearerAuth")
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")
//                                         .description("JWT Authentication")));
//     }
// }