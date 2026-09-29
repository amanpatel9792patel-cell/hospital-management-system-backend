package com.hospitalmanagement.service;

import com.hospitalmanagement.entity.Department;
import com.hospitalmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository) {

        this.departmentRepository = departmentRepository;
    }

    // CREATE
    public Department addDepartment(Department department) {

        if (departmentRepository.existsByNameIgnoreCase(
                department.getName())) {

            throw new RuntimeException(
                    "Department already exists: "
                            + department.getName()
            );
        }

        return departmentRepository.save(department);
    }

    // GET ALL
    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }

    // GET BY ID
    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Department not found with id: " + id
                        )
                );
    }

    // UPDATE
    public Department updateDepartment(
            Long id,
            Department department) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Department not found with id: "
                                                + id
                                )
                        );

        existingDepartment.setName(
                department.getName()
        );

        existingDepartment.setDescription(
                department.getDescription()
        );

        existingDepartment.setLocation(
                department.getLocation()
        );

        return departmentRepository.save(
                existingDepartment
        );
    }

    // DELETE
    public void deleteDepartment(Long id) {

        if (!departmentRepository.existsById(id)) {

            throw new RuntimeException(
                    "Department not found with id: " + id
            );
        }

        departmentRepository.deleteById(id);
    }

    // SEARCH BY NAME
    public Department searchByName(String name) {

        return departmentRepository
                .findByNameIgnoreCase(name)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Department not found: " + name
                        )
                );
    }
}