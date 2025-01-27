package com.rts.tap.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.service.BusinessUnitService;

@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping(APIConstants.BASE_URL)
public class BusinessUnitController {

    private final BusinessUnitService businessUnitService;
    private static final Logger logger = LoggerFactory.getLogger(BusinessUnitController.class);

    public BusinessUnitController(BusinessUnitService businessUnitService) {
        super();
        this.businessUnitService = businessUnitService;
    }

    /**
     * Adds a new Business Unit.
     *
     * @param businessUnit the Business Unit object to be added
     * @return a ResponseEntity containing a success message with the appropriate HTTP status
     */
    @PostMapping(APIConstants.ADD_BUSINESSUNIT_URL)
    public ResponseEntity<String> addBusinessUnit(@RequestBody BusinessUnit businessUnit) {
        logger.info("Adding new Business Unit: {}", businessUnit);
        businessUnitService.addBusinessUnit(businessUnit);
        return ResponseEntity.status(HttpStatus.CREATED) // Status 201 Created
                             .body(MessageConstants.SUCCESS_MESSAGE);
    }

    /**
     * Retrieves a Business Unit by its location.
     *
     * @param location the location of the Business Unit
     * @return a ResponseEntity containing the Business Unit object
     */
    @GetMapping(APIConstants.GET_BUSINESSUNIT_BY_LOCATION_URL)
    public ResponseEntity<BusinessUnit> getBusinessUnitByLocation(@PathVariable String location) {
        logger.info("Retrieving Business Unit by location: {}", location);
        BusinessUnit businessUnit = businessUnitService.getBusinessUnitByLocation(location);
        return ResponseEntity.ok(businessUnit); // Status 200 OK
    }

    /**
     * Retrieves all Business Units.
     *
     * @return a ResponseEntity containing the list of Business Unit objects
     */
    @GetMapping(APIConstants.GETALL_BUSINESSUNIT_URL)
    public ResponseEntity<List<BusinessUnit>> getAllBusinessUnits() {
        logger.info("Retrieving all Business Units");
        List<BusinessUnit> businessUnits = businessUnitService.getAllBusinessUnits();
        return ResponseEntity.ok(businessUnits); // Status 200 OK
    }

    /**
     * Updates an existing Business Unit.
     *
     * @param id the ID of the Business Unit to be updated
     * @param businessUnit the updated Business Unit object
     * @return a ResponseEntity containing a success message with the appropriate HTTP status
     */
    @PutMapping(APIConstants.UPDATE_BUSINESSUNIT_URL)
    public ResponseEntity<String> updateBusinessUnit(@PathVariable Long id, @RequestBody BusinessUnit businessUnit) {
        logger.info("Updating Business Unit with ID: {}", id);
        businessUnitService.updateBusinessUnit(id, businessUnit);
        return ResponseEntity.ok(MessageConstants.SUCCESS_MESSAGE); // Status 200 OK
    }
}