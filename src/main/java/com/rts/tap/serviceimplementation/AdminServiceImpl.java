package com.rts.tap.serviceimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.rts.tap.dao.AdminDao;
import com.rts.tap.model.Admin;
import com.rts.tap.service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public Admin addAdmin(Admin admin) {
        try {
            adminDao.save(admin);
            logger.info("Admin added successfully: {}", admin);
    		return admin;

        } catch (Exception e) {
            logger.error("Error while adding admin: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add admin");
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        try {
            // Implementation to retrieve all admins
            logger.info("Retrieving all admins");
            List<Admin> admins = adminDao.findAll(); // You'll need to implement this method in AdminDao
            logger.info("Admins retrieved successfully: {}", admins);
            return admins;
        } catch (Exception e) {
            logger.error("Error while retrieving admins: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve admins");
        }
    }

    @Override
    public Admin getAdminById(Long id) {
        try {
            // Implementation to retrieve an admin by ID
            logger.info("Retrieving admin with ID: {}", id);
            Admin admin = adminDao.findById(id); // You'll need to implement this method in AdminDao
            if (admin != null) {
                logger.info("Admin retrieved successfully: {}", admin);
            } else {
                logger.warn("No admin found with ID: {}", id);
            }
            return admin;
        } catch (Exception e) {
            logger.error("Error while retrieving admin with ID: {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve admin");
        }
    }
}