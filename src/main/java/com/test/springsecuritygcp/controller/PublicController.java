package com.test.springsecuritygcp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/v1/public")
public class PublicController {

    @GetMapping("/getMessage")
    public ResponseEntity<String> getMessage() {
        String message = """
                Welcome to the application.
                Please login to access all the resources available.
                """;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
