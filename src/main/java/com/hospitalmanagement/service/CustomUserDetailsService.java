package com.hospitalmanagement.service;

import com.hospitalmanagement.entity.User;
import com.hospitalmanagement.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + username
                        )
                );

        /*
         * Database:
         *
         * ADMIN
         *
         * Spring Security:
         *
         * ROLE_ADMIN
         */

        String role = user.getRole();

        if (role == null || role.isBlank()) {
            role = "USER";
        }

        role = role.toUpperCase();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(
                                "ROLE_" + role
                        )
                )
        );
    }
}