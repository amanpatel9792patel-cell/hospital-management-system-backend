package com.hospitalmanagement.controller;

import com.hospitalmanagement.entity.User;
import com.hospitalmanagement.repository.UserRepository;
import com.hospitalmanagement.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175"
})
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // Validate username
        if (user.getUsername() == null ||
                user.getUsername().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "message",
                            "Username is required"
                    ));
        }

        // Validate password
        if (user.getPassword() == null ||
                user.getPassword().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "message",
                            "Password is required"
                    ));
        }

        // Check duplicate username
        if (userRepository
                .findByUsername(user.getUsername())
                .isPresent()) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message",
                            "Username already exists"
                    ));
        }

        // Encrypt password
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        // Default role
        if (user.getRole() == null ||
                user.getRole().isBlank()) {

            user.setRole("USER");
        }

        // Save user
        User savedUser = userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message",
                        "User registered successfully",

                        "username",
                        savedUser.getUsername(),

                        "role",
                        savedUser.getRole()
                ));
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody User user
    ) {

        // Authenticate username and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        // Find authenticated user from database
        User authenticatedUser = userRepository
                .findByUsername(user.getUsername())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        )
                );

        // Generate JWT token
        String token = jwtService.generateToken(
                authenticatedUser.getUsername()
        );

        // Return login response
        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Login successful",

                        "token",
                        token,

                        "username",
                        authenticatedUser.getUsername(),

                        "role",
                        authenticatedUser.getRole()
                )
        );
    }
}