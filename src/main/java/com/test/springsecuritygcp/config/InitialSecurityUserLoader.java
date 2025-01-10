package com.test.springsecuritygcp.config;

import com.test.springsecuritygcp.model.SecurityUser;
import com.test.springsecuritygcp.repository.SecurityUserRepository;
import java.util.List;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialSecurityUserLoader {

  @Bean
  public CommandLineRunner loadInitialLoaders(
      PasswordEncoder passwordEncoder, SecurityUserRepository securityUserRepository) {
    return (args) -> {
      SecurityUser userDetails =
          SecurityUser.builder()
              .username("user")
              .password(passwordEncoder.encode("User@123"))
              .roles(Set.of("USER"))
              .build();

      SecurityUser adminDetails =
          SecurityUser.builder()
              .username("admin")
              .password(passwordEncoder.encode("Admin@123"))
              .roles(Set.of("USER", "ADMIN"))
              .build();

      securityUserRepository.saveAll(List.of(userDetails, adminDetails));
    };
  }
}
