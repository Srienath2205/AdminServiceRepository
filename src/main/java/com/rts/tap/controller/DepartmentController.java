package com.rts.tap.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.Department;
import com.rts.tap.service.DepartmentService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Adds a new Department.
     *
     * @param department the Department object to be added
     * @return a success message
     */
    @PostMapping(APIConstants.ADD_DEPARTMENT_URL)
    public String addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return MessageConstants.SUCCESS_MESSAGE;
    }

    /**
     * Retrieves all Departments.
     *
     * @return a List of Department objects
     */
    @GetMapping(APIConstants.GETALL_DEPARTMENT_URL)
    public List<Department> viewAllDepartments() {
        return departmentService.getAllDepartments();
    }

    /**
     * Updates an existing Department.
     *
     * @param id the ID of the Department to be updated
     * @param department the updated Department object
     * @return a success message
     */
    @PutMapping("/updatedepartment/{id}")
    public String updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        department.setDepartmentId(id);
        departmentService.updateDepartment(department);
        return "Department updated successfully!";
    }

    /**
     * Retrieves a Department by its ID.
     *
     * @param id the ID of the Department
     * @return the Department object
     */
    @GetMapping(APIConstants.GET_DEPARTMENT_BY_ID_URL)
    public Department getDepartmentById(@PathVariable Long id) {
        // Directly call the service layer without any additional checks
        return departmentService.getDepartmentById(id);
    }
}