package com.rts.tap.serviceimplementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.service.BusinessUnitService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

    private final BusinessUnitDao businessUnitDao;
    private static final Logger logger = LoggerFactory.getLogger(BusinessUnitServiceImpl.class);

    public BusinessUnitServiceImpl(BusinessUnitDao businessUnitDao) {
        this.businessUnitDao = businessUnitDao;
    }

    /**
     * Adds a new business unit to the system.
     *
     * @param businessUnit the business unit object to be added
     */
    @Override
    public void addBusinessUnit(BusinessUnit businessUnit) {
        try {
            businessUnitDao.save(businessUnit);
            logger.info("Business unit added successfully: {}", businessUnit);
        } catch (Exception e) {
            logger.error("Error while adding business unit: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to add business unit: " + e.getMessage());
        }
    }

    /**
     * Retrieves a business unit by its location.
     *
     * @param workLocation the location of the business unit
     * @return the business unit object
     */
    @Override
    public BusinessUnit getBusinessUnitByLocation(String workLocation) {
        try {
            return businessUnitDao.getBusinessUnitByLocation(workLocation);
        } catch (Exception e) {
            logger.error("Error while retrieving business unit by location: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve business unit by location: " + e.getMessage());
        }
    }

    /**
     * Retrieves a business unit by its ID.
     *
     * @param businessunitId the ID of the business unit
     * @return the business unit object
     */
    @Override
    public BusinessUnit getBusinessUnitById(Long businessunitId) {
        try {
            return businessUnitDao.findById(businessunitId);
        } catch (Exception e) {
            logger.error("Error while retrieving business unit by ID: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve business unit by ID: " + e.getMessage());
        }
    }

    /**
     * Retrieves all business units in the system.
     *
     * @return a list of business unit objects
     */
    @Override
    public List<BusinessUnit> getAllBusinessUnits() {
        try {
            return businessUnitDao.findAll();
        } catch (Exception e) {
            logger.error("Error while retrieving all business units: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to retrieve all business units: " + e.getMessage());
        }
    }

    /**
     * Updates an existing business unit.
     *
     * @param id the ID of the business unit
     * @param updatedBusinessUnit the updated business unit object
     */
    @Override
    public void updateBusinessUnit(Long id, BusinessUnit updatedBusinessUnit) {
        try {
            BusinessUnit existingBusinessUnit = businessUnitDao.findById(id);

            if (existingBusinessUnit != null) {
                existingBusinessUnit.setBusinessUnitName(updatedBusinessUnit.getBusinessUnitName());
                existingBusinessUnit.setDescription(updatedBusinessUnit.getDescription());
                existingBusinessUnit.setBusinessUnitLocation(updatedBusinessUnit.getBusinessUnitLocation());
                businessUnitDao.save(existingBusinessUnit);
                logger.info("Business unit updated successfully: {}", existingBusinessUnit);
            } else {
                throw new RuntimeException("Business unit not found with ID " + id);
            }
        } catch (Exception e) {
            logger.error("Error while updating business unit: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update business unit: " + e.getMessage());
        }
    }
}