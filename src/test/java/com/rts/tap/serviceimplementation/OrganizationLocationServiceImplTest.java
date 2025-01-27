package com.rts.tap.serviceimplementation;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.OrganizationLocationDao;
import com.rts.tap.model.OrganizationLocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrganizationLocationServiceImplTest {

    @Mock
    private OrganizationLocationDao organizationLocationDao;

    @InjectMocks
    private OrganizationLocationServiceImpl organizationLocationService;

    @Test
    public void testAddOrganizationLocation() {
        // Given
        OrganizationLocation organizationLocation = new OrganizationLocation();
        organizationLocation.setLocationId(null); // New location, so ID is null

        // When
        when(organizationLocationDao.save(any(OrganizationLocation.class))).thenAnswer(invocation -> {((OrganizationLocation) invocation.getArguments()[0]).setLocationId(1L); // Simulate database generated ID
            return null;
        });

        // Then
        String result = organizationLocationService.addOrganizationLocation(organizationLocation);
        assertEquals(MessageConstants.ORG_LOCATION_CREATED_SUCCESS, result);
        verify(organizationLocationDao, times(1)).save(organizationLocation);
    }

    @Test
    public void testAddOrganizationLocation_Fails() {
        // Given
        OrganizationLocation organizationLocation = new OrganizationLocation();
        organizationLocation.setLocationId(null); // New location, so ID is null

        // When
        when(organizationLocationDao.save(any(OrganizationLocation.class))).thenThrow(new RuntimeException("Error while adding location"));

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> organizationLocationService.addOrganizationLocation(organizationLocation));
        assertEquals(MessageConstants.ORG_LOCATION_CREATION_FAILED + ": Error while adding location", thrownException.getMessage());
        verify(organizationLocationDao, times(1)).save(organizationLocation);
    }

    @Test
    public void testGetAllOrganizationLocations() {
        // Given
        OrganizationLocation orgLocation1 = new OrganizationLocation();
        orgLocation1.setLocationId(1L);
        orgLocation1.setLocationName("Location 1");

        OrganizationLocation orgLocation2 = new OrganizationLocation();
        orgLocation2.setLocationId(2L);
        orgLocation2.setLocationName("Location 2");

        List<OrganizationLocation> locations = new ArrayList<>();
        locations.add(orgLocation1);
        locations.add(orgLocation2);

        // When
        when(organizationLocationDao.findAll()).thenReturn(locations);

        // Then
        List<OrganizationLocation> result = organizationLocationService.getAllOrganizationLocations();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Location 1", result.get(0).getLocationName());
        verify(organizationLocationDao, times(1)).findAll();
    }

    @Test
    public void testGetAllOrganizationLocations_Fails() {
        // When
        when(organizationLocationDao.findAll()).thenThrow(new RuntimeException("Error while retrieving locations"));

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> organizationLocationService.getAllOrganizationLocations());
        assertEquals(MessageConstants.INTERNAL_SERVER_ERROR, thrownException.getMessage());
        verify(organizationLocationDao, times(1)).findAll();
    }

    @Test
    public void testGetOrganizationLocationById() {
        // Given
        OrganizationLocation orgLocation = new OrganizationLocation();
        orgLocation.setLocationId(1L);
        orgLocation.setLocationName("Location 1");

        // When
        when(organizationLocationDao.findById(1L)).thenReturn(Optional.of(orgLocation));

        // Then
        OrganizationLocation result = organizationLocationService.getOrganizationLocationById(1L);
        assertNotNull(result);
        assertEquals("Location 1", result.getLocationName());
        verify(organizationLocationDao, times(1)).findById(1L);
    }

    @Test
    public void testGetOrganizationLocationById_NotFound() {
        // When
        when(organizationLocationDao.findById(1L)).thenReturn(Optional.empty());

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> organizationLocationService.getOrganizationLocationById(1L));
        assertEquals(MessageConstants.INTERNAL_SERVER_ERROR + ": " + MessageConstants.ORG_LOCATION_NOT_FOUND + 1L, thrownException.getMessage());
        verify(organizationLocationDao, times(1)).findById(1L);
    }

    @Test
    public void testGetOrganizationLocationById_Fails() {
        // When
        when(organizationLocationDao.findById(1L)).thenThrow(new RuntimeException("Database error"));

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> organizationLocationService.getOrganizationLocationById(1L));
        assertEquals(MessageConstants.INTERNAL_SERVER_ERROR + ": Database error", thrownException.getMessage());
        verify(organizationLocationDao, times(1)).findById(1L);
    }

    @Test
    public void testUpdateOrganizationLocation() {
        // Given
        OrganizationLocation organizationLocation = new OrganizationLocation();
        organizationLocation.setLocationId(1L);
        organizationLocation.setLocationName("Updated Location");

        // When
        when(organizationLocationDao.save(any(OrganizationLocation.class))).thenReturn(null);

        // Then
        String result = organizationLocationService.updateOrganizationLocation(organizationLocation);
        assertEquals(MessageConstants.ORG_LOCATION_UPDATED_SUCCESS, result);
        verify(organizationLocationDao, times(1)).save(organizationLocation);
    }

    @Test
    public void testUpdateOrganizationLocation_Fails() {
        // Given
        OrganizationLocation organizationLocation = new OrganizationLocation();
        organizationLocation.setLocationId(1L);
        organizationLocation.setLocationName("Updated Location");

        // When
        when(organizationLocationDao.save(any(OrganizationLocation.class))).thenThrow(new RuntimeException("Error while updating location"));

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> organizationLocationService.updateOrganizationLocation(organizationLocation));
        assertEquals(MessageConstants.ORG_LOCATION_UPDATE_FAILED + ": Error while updating location", thrownException.getMessage());
        verify(organizationLocationDao, times(1)).save(organizationLocation);
    }
}