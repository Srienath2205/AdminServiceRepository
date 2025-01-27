package com.rts.tap.serviceimplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.model.BusinessUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessUnitServiceImplTest {

    @Mock
    private BusinessUnitDao businessUnitDaoMock;

    @InjectMocks
    private BusinessUnitServiceImpl businessUnitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBusinessUnitSuccess() {
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessUnitName("Sales");
        businessUnit.setDescription("Sales Department");
        businessUnit.setBusinessUnitLocation("New York");

        // Test the addBusinessUnit method
        businessUnitService.addBusinessUnit(businessUnit);
        verify(businessUnitDaoMock, times(1)).save(businessUnit);
    }

    @Test
    void testAddBusinessUnitFailure() {
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessUnitName("Sales");
        businessUnit.setDescription("Sales Department");
        businessUnit.setBusinessUnitLocation("New York");

        // Mocking the exception to be thrown when save is called
        doThrow(new RuntimeException("Database error")).when(businessUnitDaoMock).save(any());

        // Expectation of exception
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            businessUnitService.addBusinessUnit(businessUnit);
        });

        assertEquals("Failed to add business unit: Database error", thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).save(businessUnit);
    }

    @Test
    void testGetBusinessUnitByLocationSuccess() {
        String location = "New York";
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessUnitLocation(location);
        businessUnit.setBusinessUnitName("Sales");

        when(businessUnitDaoMock.getBusinessUnitByLocation(location)).thenReturn(businessUnit);

        BusinessUnit retrievedUnit = businessUnitService.getBusinessUnitByLocation(location);
        assertNotNull(retrievedUnit);
        assertEquals("Sales", retrievedUnit.getBusinessUnitName());
        verify(businessUnitDaoMock, times(1)).getBusinessUnitByLocation(location);
    }

    @Test
    void testGetBusinessUnitByLocationFailure() {
        String location = "Non-existent Location";
        when(businessUnitDaoMock.getBusinessUnitByLocation(location)).thenThrow(new RuntimeException("Error retrieving business unit"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            businessUnitService.getBusinessUnitByLocation(location);
        });

        assertEquals("Failed to retrieve business unit by location: Error retrieving business unit", thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).getBusinessUnitByLocation(location);
    }

    @Test
    void testGetBusinessUnitByIdSuccess() {
        Long id = 1L;
        BusinessUnit businessUnit = new BusinessUnit();
        businessUnit.setBusinessunitId(id);
        businessUnit.setBusinessUnitName("Sales");

        when(businessUnitDaoMock.findById(id)).thenReturn(businessUnit);

        BusinessUnit retrievedUnit = businessUnitService.getBusinessUnitById(id);
        assertNotNull(retrievedUnit);
        assertEquals("Sales", retrievedUnit.getBusinessUnitName());
        verify(businessUnitDaoMock, times(1)).findById(id);
    }

    @Test
    void testGetBusinessUnitByIdFailure() {
        Long id = 1L;
        when(businessUnitDaoMock.findById(id)).thenThrow(new RuntimeException("Error retrieving business unit"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            businessUnitService.getBusinessUnitById(id);
        });

        assertEquals("Failed to retrieve business unit by ID: Error retrieving business unit", thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).findById(id);
    }

    @Test
    void testGetAllBusinessUnitsSuccess() {
        BusinessUnit businessUnit1 = new BusinessUnit();
        businessUnit1.setBusinessunitId(1L);
        businessUnit1.setBusinessUnitName("Sales");

        BusinessUnit businessUnit2 = new BusinessUnit();
        businessUnit2.setBusinessunitId(2L);
        businessUnit2.setBusinessUnitName("Marketing");

        when(businessUnitDaoMock.findAll()).thenReturn(List.of(businessUnit1, businessUnit2));

        List<BusinessUnit> businessUnits = businessUnitService.getAllBusinessUnits();
        assertEquals(2, businessUnits.size());
        verify(businessUnitDaoMock, times(1)).findAll();
    }

    @Test
    void testGetAllBusinessUnitsFailure() {
        when(businessUnitDaoMock.findAll()).thenThrow(new RuntimeException("Error retrieving all business units"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            businessUnitService.getAllBusinessUnits();
        });

        assertEquals("Failed to retrieve all business units: Error retrieving all business units", thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).findAll();
    }

    @Test
    void testUpdateBusinessUnitSuccess() {
        Long id = 1L;
        BusinessUnit existingUnit = new BusinessUnit();
        existingUnit.setBusinessunitId(id);
        existingUnit.setBusinessUnitName("Sales");
        existingUnit.setBusinessUnitLocation("New York");

        BusinessUnit updatedUnit = new BusinessUnit();
        updatedUnit.setBusinessUnitName("Sales Updated");
        updatedUnit.setDescription("Sales Department Updated");
        updatedUnit.setBusinessUnitLocation("San Francisco");

        when(businessUnitDaoMock.findById(id)).thenReturn(existingUnit);

        businessUnitService.updateBusinessUnit(id, updatedUnit);

        assertEquals("Sales Updated", existingUnit.getBusinessUnitName());
        assertEquals("Sales Department Updated", existingUnit.getDescription());
        assertEquals("San Francisco", existingUnit.getBusinessUnitLocation());
        
        verify(businessUnitDaoMock, times(1)).save(existingUnit);
    }

    @Test
    void testUpdateBusinessUnitFailure() {
        Long id = 1L;
        BusinessUnit existingUnit = new BusinessUnit();
        existingUnit.setBusinessunitId(id);
        existingUnit.setBusinessUnitName("Sales");
        existingUnit.setBusinessUnitLocation("New York");

        BusinessUnit updatedUnit = new BusinessUnit();
        updatedUnit.setBusinessUnitName("Sales Updated");

        when(businessUnitDaoMock.findById(id)).thenReturn(existingUnit);
        doThrow(new RuntimeException("Database error")).when(businessUnitDaoMock).save(any());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            businessUnitService.updateBusinessUnit(id, updatedUnit);
        });

        assertEquals("Failed to update business unit: Database error", thrown.getMessage());
        verify(businessUnitDaoMock, times(1)).findById(id);
        verify(businessUnitDaoMock, times(1)).save(existingUnit);
    }
}