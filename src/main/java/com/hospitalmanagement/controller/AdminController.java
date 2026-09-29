package com.hospitalmanagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "http://localhost:5174"
        }
)
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {

        return "Admin Dashboard Access Granted";
    }

    @GetMapping("/test")
    public String test() {

        return "ADMIN authorization is working successfully";
    }
}