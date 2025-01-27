package com.rts.tap.serviceimplementation;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.OrganizationLocationDao;
import com.rts.tap.model.OrganizationLocation;
import com.rts.tap.service.OrganizationLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationLocationServiceImpl implements OrganizationLocationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationLocationServiceImpl.class);
    private final OrganizationLocationDao organizationLocationDao;

    public OrganizationLocationServiceImpl(OrganizationLocationDao organizationLocationDao) {
        this.organizationLocationDao = organizationLocationDao;
    }

    @Override
    public String addOrganizationLocation(OrganizationLocation organizationLocation) {
        try {
            logger.info("Adding organization location: {}", organizationLocation);
            organizationLocationDao.save(organizationLocation);
            logger.info("Organization location added successfully: {}", organizationLocation);
            return MessageConstants.ORG_LOCATION_CREATED_SUCCESS;
        } catch (Exception e) {
            logger.error("Error creating organization location: {}", e.getMessage(), e);
            throw new RuntimeException(MessageConstants.ORG_LOCATION_CREATION_FAILED + ": " + e.getMessage());
        }
    }

    @Override
    public List<OrganizationLocation> getAllOrganizationLocations() {
        try {
            logger.info("Retrieving all organization locations");
            List<OrganizationLocation> locations = organizationLocationDao.findAll();
            logger.info("Retrieved {} organization locations", locations.size());
            return locations;
        } catch (Exception e) {
            logger.error("Error retrieving organization locations: {}", e.getMessage(), e);
            throw new RuntimeException(MessageConstants.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public OrganizationLocation getOrganizationLocationById(Long id) {
        try {
            logger.info("Retrieving organization location by ID: {}", id);
            Optional<OrganizationLocation> organizationLocation = organizationLocationDao.findById(id);
            if (organizationLocation.isPresent()) {
                logger.info("Organization location found: {}", organizationLocation.get());
                return organizationLocation.get();  // Return the found location
            } else {
                logger.warn("No organization location found with ID: {}", id);
                throw new RuntimeException(MessageConstants.ORG_LOCATION_NOT_FOUND + id);
            }
        } catch (Exception e) {
            logger.error("Error retrieving organization location with ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException(MessageConstants.INTERNAL_SERVER_ERROR + ": " + e.getMessage());
        }
    }

    @Override
    public String updateOrganizationLocation(OrganizationLocation organizationLocation) {
        try {
            logger.info("Updating organization location: {}", organizationLocation);
            organizationLocationDao.save(organizationLocation);  // save will update if the entity already exists
            logger.info("Organization location updated successfully: {}", organizationLocation);
            return MessageConstants.ORG_LOCATION_UPDATED_SUCCESS;
        } catch (Exception e) {
            logger.error("Error updating organization location with ID {}: {}", organizationLocation.getLocationId(), e.getMessage(), e);
            throw new RuntimeException(MessageConstants.ORG_LOCATION_UPDATE_FAILED + ": " + e.getMessage());
        }
    }
}