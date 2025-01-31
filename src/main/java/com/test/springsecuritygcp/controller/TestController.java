package com.test.springsecuritygcp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {

    @GetMapping("/getWelcomeMessage")
    public ResponseEntity<String> getWelcomeMessagEntity() {
        String username = "guest";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString(); // In case of a simple String principal (e.g., for JWT)
        }
        return new ResponseEntity<String>(String.format("Welcome %s", username),
                HttpStatus.OK);
    }

}
