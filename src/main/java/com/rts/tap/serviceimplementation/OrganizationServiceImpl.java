package com.rts.tap.serviceimplementation;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.OrganizationDao;
import com.rts.tap.model.Organization;
import com.rts.tap.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao repo;
    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    public OrganizationServiceImpl(OrganizationDao repo) {
        this.repo = repo;
    }

    /**
     * Adds a new organization to the system.
     *
     * @param organization the organization object to be added
     */
    public void addOrganization(Organization organization) {
        try {
            repo.save(organization);
            logger.info("Organization added successfully: {}", organization);
        } catch (Exception e) {
            logger.error("Error while adding organization: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add organization: " + e.getMessage());
        }
    }

    /**
     * Retrieves all organizations from the system.
     *
     * @return list of all organizations
     */
    @Override
    public List<Organization> getAllOrganization() {
        try {
            List<Organization> organizations = repo.getAllOrganization();
            logger.info("Retrieved all organizations successfully.");
            return organizations;
        } catch (Exception e) {
            logger.error("Error while retrieving organizations: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve organizations: " + e.getMessage());
        }
    }

    /**
     * Updates the details of an existing organization.
     *
     * @param organization the organization object with updated values
     */
    @Override
    public void updateOrganization(Organization organization) {
        try {
            repo.update(organization);
            logger.info("Organization updated successfully: {}", organization);
        } catch (Exception e) {
            logger.error("Error while updating organization: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update organization: " + e.getMessage());
        }
    }
}