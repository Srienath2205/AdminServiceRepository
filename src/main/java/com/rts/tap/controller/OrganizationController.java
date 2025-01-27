/**
* Author: Team A
*
* OrganizationController handles operations related to organizations such as adding
* a new organization, retrieving a list of organizations, and updating existing
* organization details.
*
* This class interacts with the OrganizationService to perform the necessary business
* logic and returns the appropriate HTTP responses.
*/
 
package com.rts.tap.controller;
 
import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.rts.tap.model.Organization;
import com.rts.tap.service.OrganizationService;
 
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class OrganizationController {
 
    private final OrganizationService organizationService;
    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
 
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
 
    /**
     * Adds a new organization to the system.
     *
     * @param organization the organization to be added
     * @return success or failure message
     */
    @PostMapping(APIConstants.ADD_ORGANIZATION_URL)
    public String addAdmin(@RequestBody Organization organization) {
        try {
            organizationService.addOrganization(organization);
            return MessageConstants.SUCCESS_MESSAGE;
        } catch (Exception e) {
            logger.error("Error adding organization: {}", e.getMessage(), e);
            return MessageConstants.FAILURE_MESSAGE;
        }
    }
 
    /**
     * Retrieves all organizations from the system.
     *
     * @return list of all organizations
     */
    @GetMapping(APIConstants.GETALL_ORGANIZATION_URL)
    public List<Organization> viewAllOrganization() {
        return organizationService.getAllOrganization();
    }
 
    /**
     * Updates the details of an existing organization.
     *
     * @param id          the ID of the organization to be updated
     * @param organization the organization data to update
     * @return success or failure message
     */
    @PutMapping(APIConstants.UPDATE_ORGANIZATION_URL)
    public String updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
        try {
            // Ensure the correct entity is updated by setting the organization ID
            organization.setOrganizationId(id);
            organizationService.updateOrganization(organization);
            return MessageConstants.SUCCESS_MESSAGE;
        } catch (Exception e) {
            logger.error("Error updating organization with ID {}: {}", id, e.getMessage(), e);
            return MessageConstants.FAILURE_MESSAGE;
        }
    }
}
 