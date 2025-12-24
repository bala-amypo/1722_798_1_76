// package com.example.demo.security;

// public class UserPrincipal {
    
//     private Long id;
//     private String email;
//     private String password;
//     private String role;
    
//     public UserPrincipal(Long id, String email, String password, String role) {
//         this.id = id;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//     }
    
//     public Long getId() {
//         return id;
//     }
    
//     public String getEmail() {
//         return email;
//     }
    
//     public String getPassword() {
//         return password;
//     }
    
//     public String getRole() {
//         return role;
//     }
    
//     public String getUsername() {
//         return email;
//     }
// }


package com.example.demo.security;

public class UserPrincipal {
    private Long id;
    private String email;
    private String password;
    private String role;
    
    public UserPrincipal(Long id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getUsername() { return email; }
}