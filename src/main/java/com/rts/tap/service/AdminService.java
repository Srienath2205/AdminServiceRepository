package com.rts.tap.service;

import com.rts.tap.model.Admin;

import java.util.List;

public interface AdminService {
    Admin addAdmin(Admin admin);
    List<Admin> getAllAdmins(); // New method for getting all admins
    Admin getAdminById(Long id); // New method for getting an admin by ID
}