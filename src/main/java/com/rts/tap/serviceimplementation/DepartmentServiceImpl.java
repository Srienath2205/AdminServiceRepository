package com.rts.tap.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.model.Department;
import com.rts.tap.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao repo;
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    public DepartmentServiceImpl(DepartmentDao repo) {
        this.repo = repo;
    }

    /**
     * Adds a new department to the system.
     *
     * @param department the department object to be added
     */
    @Override
    public void addDepartment(Department department) {
        try {
            repo.save(department);
            logger.info("Department added successfully: {}", department);
        } catch (Exception e) {
            logger.error("Error while adding department: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add department: " + e.getMessage());
        }
    }

    /**
     * Retrieves all departments in the system.
     *
     * @return a list of department objects
     */
    @Override
    public List<Department> getAllDepartments() {
        try {
            List<Department> departments = repo.getAllDepartments();
            logger.info("Retrieved all departments successfully.");
            return departments;
        } catch (Exception e) {
            logger.error("Error while retrieving all departments: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve all departments: " + e.getMessage());
        }
    }

    /**
     * Updates an existing department.
     *
     * @param department the department object with updated values
     */
    @Override
    public void updateDepartment(Department department) {
        try {
            repo.update(department);
            logger.info("Department updated successfully: {}", department);
        } catch (Exception e) {
            logger.error("Error while updating department: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update department: " + e.getMessage());
        }
    }

    /**
     * Retrieves a department by its ID.
     *
     * @param id the ID of the department
     * @return the department object
     */
    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> department = repo.findById(id);
        if (department.isPresent()) {
            logger.info("Retrieved department successfully: {}", department.get());
            return department.get();
        } else {
            logger.warn("Department not found with ID: {}", id);
            throw new RuntimeException("Department not found with ID: " + id);
        }
    }
}