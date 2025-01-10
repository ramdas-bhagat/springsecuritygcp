package com.test.springsecuritygcp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.springsecuritygcp.model.SecurityUser;

@Repository
public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {
    
    Optional<SecurityUser> findByUsername(String username); 
}
