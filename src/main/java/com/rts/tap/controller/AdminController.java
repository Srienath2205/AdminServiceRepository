package com.rts.tap.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.Admin;
import com.rts.tap.service.AdminService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class AdminController {

    private final AdminService adminService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(APIConstants.ADD_ADMIN_URL)
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
        logger.info("Adding new Admin: {}", admin);
        adminService.addAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MessageConstants.SUCCESS_MESSAGE);
    }

    /**
     * Retrieves all admins from the system.
     *
     * @return a ResponseEntity containing a success message and a list of admins,
     * or a ResponseEntity containing a failure message and an empty list
     */
    @GetMapping(APIConstants.GET_ALL_ADMINS_URL)
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    /**
     * Retrieves an admin by ID.
     *
     * @param id the ID of the admin
     * @return a ResponseEntity containing the found admin,
     * or a ResponseEntity containing a failure message indicating the admin was not found
     */
    @GetMapping(APIConstants.GET_ADMIN_BY_ID_URL)
    public ResponseEntity<Object> getAdminById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }
}