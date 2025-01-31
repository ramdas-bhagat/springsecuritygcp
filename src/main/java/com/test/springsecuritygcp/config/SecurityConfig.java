package com.test.springsecuritygcp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Uncomment below line if want inmemory user details manager for now switching
  // to db based.
  // @Bean
  public UserDetailsService getUserDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails userDetails = User.builder()
        .username("user")
        .password("User@123")
        .passwordEncoder(passwordEncoder::encode)
        .roles("USER")
        .build();

    UserDetails adminDetails = User.builder()
        .username("admin")
        .password("Admin@123")
        .passwordEncoder(passwordEncoder::encode)
        .roles("USER", "ADMIN")
        .build();

    return new InMemoryUserDetailsManager(userDetails, adminDetails);
  }

  @Bean
  public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/v1/public/**").permitAll()
        .anyRequest().authenticated());

    http.formLogin(Customizer.withDefaults());
    // http.httpBasic(Customizer.withDefaults());

    http.csrf(csrf -> csrf.disable());
    http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
    http.cors(Customizer.withDefaults());

    return http.build();
  }
}
