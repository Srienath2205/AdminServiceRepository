package com.rts.tap.serviceimplementation;

import com.rts.tap.dao.OrganizationDao;
import com.rts.tap.model.Organization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrganizationServiceImplTest {

    @Mock
    private OrganizationDao organizationDao;

    @InjectMocks
    private OrganizationServiceImpl organizationService;

    @Test
    public void testAddOrganization() {
        Organization organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("Test Organization");

        doNothing().when(organizationDao).save(any(Organization.class));

        assertDoesNotThrow(() -> organizationService.addOrganization(organization));
        verify(organizationDao, times(1)).save(organization);
    }

    @Test
    public void testAddOrganization_Fails() {
        Organization organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("Test Organization");

        doThrow(new RuntimeException("Error while adding organization")).when(organizationDao).save(any(Organization.class));

        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> organizationService.addOrganization(organization));
        assertEquals("Failed to add organization: Error while adding organization", thrownException.getMessage());
        verify(organizationDao, times(1)).save(organization);
    }

    @Test
    public void testGetAllOrganization() {
        Organization org1 = new Organization();
        org1.setOrganizationId(1L);
        org1.setOrganizationName("Test Organization 1");

        Organization org2 = new Organization();
        org2.setOrganizationId(2L);
        org2.setOrganizationName("Test Organization 2");

        List<Organization> organizations = new ArrayList<>();
        organizations.add(org1);
        organizations.add(org2);

        when(organizationDao.getAllOrganization()).thenReturn(organizations);

        List<Organization> result = organizationService.getAllOrganization();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(organizationDao, times(1)).getAllOrganization();
    }

    @Test
    public void testGetAllOrganization_Fails() {
        when(organizationDao.getAllOrganization()).thenThrow(new RuntimeException("Error while retrieving organizations"));

        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> organizationService.getAllOrganization());
        assertEquals("Failed to retrieve organizations: Error while retrieving organizations", thrownException.getMessage());
        verify(organizationDao, times(1)).getAllOrganization();
    }

    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("Updated Organization");

        doNothing().when(organizationDao).update(any(Organization.class));

        assertDoesNotThrow(() -> organizationService.updateOrganization(organization));
        verify(organizationDao, times(1)).update(organization);
    }

    @Test
    public void testUpdateOrganization_Fails() {
        Organization organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("Updated Organization");

        doThrow(new RuntimeException("Error while updating organization")).when(organizationDao).update(any(Organization.class));

        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> organizationService.updateOrganization(organization));
        assertEquals("Failed to update organization: Error while updating organization", thrownException.getMessage());
        verify(organizationDao, times(1)).update(organization);
    }
}