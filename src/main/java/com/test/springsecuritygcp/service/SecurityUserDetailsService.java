package com.test.springsecuritygcp.service;

import com.test.springsecuritygcp.model.SecurityUser;
import com.test.springsecuritygcp.repository.SecurityUserRepository;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

  private SecurityUserRepository securityUserRepository;

  public SecurityUserDetailsService(SecurityUserRepository securityUserRepository) {
    super();
    this.securityUserRepository = securityUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SecurityUser securityUser =
        securityUserRepository
            .findByUsername(username)
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        String.format("Username: %s not found.", username)));
    return User.builder()
        .username(securityUser.getUsername())
        .password(securityUser.getPassword())
        .authorities(
            securityUser.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()))
        .build();
  }
}
