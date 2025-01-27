package com.rts.tap.serviceimplementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.RoleDao;
import com.rts.tap.model.Role;
import com.rts.tap.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public String addRole(Role role) {
        try {
            logger.info("Adding role: {}", role);
            roleDao.save(role);
            logger.info("Role added successfully: {}", role);
            return MessageConstants.SUCCESS_MESSAGE;
        } catch (Exception e) {
            logger.error("Error adding role: {}", e.getMessage(), e);
            throw new RuntimeException(MessageConstants.FAILURE_MESSAGE);
        }
    }

    @Override
    public List<Role> getAllRole() {
        try {
            logger.info("Fetching all roles");
            List<Role> roles = roleDao.getAllRole();
            logger.info("Roles fetched successfully: {}", roles);
            return roles;
        } catch (Exception e) {
            logger.error("Error fetching roles: {}", e.getMessage(), e);
            throw new RuntimeException(MessageConstants.FAILURE_MESSAGE);
        }
    }

    @Override
    public String updateRole(Role role) {
        try {
            logger.info("Updating role: {}", role);
            roleDao.update(role);
            logger.info("Role updated successfully: {}", role);
            return MessageConstants.SUCCESS_MESSAGE;
        } catch (Exception e) {
            logger.error("Error updating role: {}", e.getMessage(), e);
            throw new RuntimeException(MessageConstants.FAILURE_MESSAGE);
        }
    }

    @Override
    public Role getRoleById(Long id) {
        try {
            logger.info("Fetching role with ID: {}", id);
            Role role = roleDao.getRoleById(id);
            if (role != null) {
                logger.info("Role fetched successfully: {}", role);
            } else {
                logger.warn("No role found with ID: {}", id);
            }
            return role;
        } catch (Exception e) {
            logger.error("Error fetching role with ID: {}: {}", id, e.getMessage(), e);
            throw new RuntimeException(MessageConstants.FAILURE_MESSAGE);
        }
    }
}