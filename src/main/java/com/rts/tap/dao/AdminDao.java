package com.rts.tap.dao;

import com.rts.tap.model.Admin;

import java.util.List;

public interface AdminDao {
    Admin save(Admin admin);
    Admin findEmail(String username);
    List<Admin> findAll(); // New method for retrieving all admins
    Admin findById(Long id); // New method for finding admin by ID
}