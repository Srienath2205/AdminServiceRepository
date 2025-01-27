package com.rts.tap.controller;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.OrganizationLocation;
import com.rts.tap.service.OrganizationLocationService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIConstants.BASE_URL)
@CrossOrigin(APIConstants.CROSS_ORIGIN_URL)
public class OrganizationLocationController {

    private final OrganizationLocationService organizationLocationService;

    public OrganizationLocationController(OrganizationLocationService organizationLocationService) {
        this.organizationLocationService = organizationLocationService;
    }

    /**
     * Creates a new OrganizationLocation.
     *
     * @param organizationLocation the organization location to be created
     * @return response entity indicating the result of the creation
     */
    @PostMapping(APIConstants.ADD_ORG_LOCATION_URL)
    public ResponseEntity<String> addOrganizationLocation(@RequestBody OrganizationLocation organizationLocation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationLocationService.addOrganizationLocation(organizationLocation));
    }

    /**
     * Retrieves all organization locations.
     *
     * @return a list of organization locations or a NO_CONTENT response if none exist
     */
    @GetMapping(APIConstants.GET_ALL_ORG_LOCATIONS_URL)
    public ResponseEntity<List<OrganizationLocation>> getAllOrganizationLocations() {
        List<OrganizationLocation> locations = organizationLocationService.getAllOrganizationLocations();
        return locations.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(locations) : ResponseEntity.ok(locations);
    }

    /**
     * Retrieves organization location by ID.
     *
     * @param id the ID of the organization location
     * @return the organization location or a NOT_FOUND response if not found
     */
    @GetMapping(APIConstants.GET_ORG_LOCATION_BY_ID_URL)
    public ResponseEntity<Object> getOrganizationLocationById(@PathVariable Long id) {
        OrganizationLocation location = organizationLocationService.getOrganizationLocationById(id);
        return location != null ? ResponseEntity.ok(location) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageConstants.ORG_LOCATION_NOT_FOUND + id);
    }

    /**
     * Updates an existing OrganizationLocation by ID.
     *
     * @param id                   the ID of the organization location to be updated
     * @param organizationLocation the updated organization location data
     * @return response entity indicating the result of the update
     */
    @PutMapping(APIConstants.UPDATE_ORG_LOCATION_URL)
    public ResponseEntity<String> updateOrganizationLocation(@PathVariable Long id, @RequestBody OrganizationLocation organizationLocation) {
        organizationLocation.setLocationId(id); // Ensure ID is set for update
        return ResponseEntity.ok(organizationLocationService.updateOrganizationLocation(organizationLocation));
    }
}