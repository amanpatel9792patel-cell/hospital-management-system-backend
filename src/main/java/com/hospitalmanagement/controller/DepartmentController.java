package com.hospitalmanagement.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import com.hospitalmanagement.entity.Department;
import com.hospitalmanagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(
            DepartmentService departmentService) {

        this.departmentService = departmentService;
    }

    // CREATE
    @PostMapping
    public Department addDepartment(
            @Valid @RequestBody Department department) {

        return departmentService.addDepartment(department);
    }

    // GET ALL
    @GetMapping
    public List<Department> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Department getDepartmentById(
            @PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody Department department) {

        return departmentService.updateDepartment(
                id,
                department
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok(
                "Department deleted successfully"
        );
    }

    // SEARCH
    @GetMapping("/search")
    public Department searchByName(
            @RequestParam String name) {

        return departmentService.searchByName(name);
    }
}