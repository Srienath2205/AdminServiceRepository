package com.rts.tap.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.Role;
import com.rts.tap.service.RoleService;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
public class RoleController {

    private final RoleService roleService;
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Adds a new role to the system.
     *
     * @param role the role to be added
     * @return a success or failure message
     */
    @PostMapping(APIConstants.ADD_ROLE_URL)
    public ResponseEntity<String> addRole(@RequestBody Role role) {
        String message = roleService.addRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    /**
     * Retrieves all roles from the system.
     *
     * @return a list of roles
     */
    @GetMapping(APIConstants.GETALL_ROLE_URL)
    public ResponseEntity<List<Role>> viewAllRole() {
        List<Role> roles = roleService.getAllRole();
        if (roles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(roles);
        }
        return ResponseEntity.ok(roles);
    }

    /**
     * Retrieves a role by its ID.
     *
     * @param id the ID of the role
     * @return the role, or a NOT_FOUND response if it does not exist
     */
    @GetMapping(APIConstants.GET_ROLE_BY_ID_URL)
    public ResponseEntity<Object> getRoleById(@PathVariable("id") Long id) {
        Role role = roleService.getRoleById(id);
        return role != null ? ResponseEntity.ok(role) : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(MessageConstants.FAILURE_MESSAGE + ": Role not found for ID " + id);
    }

    /**
     * Updates an existing role.
     *
     * @param id   the ID of the role to be updated
     * @param role the role data to update
     * @return a success or failure message
     */
    @PutMapping(APIConstants.UPDATE_ROLE_URL)
    public ResponseEntity<String> updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
        role.setRoleId(id); // Ensure the role ID is set for the update
        String message = roleService.updateRole(role);
        return ResponseEntity.ok(message);
    }
}