package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/management")
public class ManagementController {

    // get put delete post

    @Autowired
    UserRepository userRepository;

    /**
     * Verify Page
     */
    @GetMapping("/verify")
    @PreAuthorize("hasRole('ROLE_LEAGUE_EXCUTIVE')")
    public ResponseEntity<?> getAllNotVerified() {
        try {
            List<User> listUsers = userRepository.getAllVerifyUsers(false);
            if (listUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/verify/{email}")
    @PreAuthorize("hasRole('ROLE_LEAGUE_EXCUTIVE')")
    public ResponseEntity<?> verifyUser(@PathVariable("email") String email) {
        try {
            Optional<User> uOptional = userRepository.findByEmail(email);
            if (uOptional.isPresent()) {
                User user = uOptional.get();
                if (!user.getVerify()) {
                    user.setVerify(true);
                }
                // userRepository.save(user);
                return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public ResponseEntity<?> deleteVerifyUser() {

    // }
}
