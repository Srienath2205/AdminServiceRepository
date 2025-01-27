package com.rts.tap.serviceimplementation;

import com.rts.tap.dao.AdminDao;
import com.rts.tap.model.Admin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @Mock
    private AdminDao adminDao;

    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @Test
    public void testAddAdmin() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        when(adminDao.save(any(Admin.class))).thenReturn(admin);
        Admin result = adminServiceImpl.addAdmin(admin);
        assertNotNull(result);
        verify(adminDao, times(1)).save(any(Admin.class));
        verify(adminDao, times(0)).findById(any(Long.class));
        verify(adminDao, times(0)).findAll();
    }

    @Test
    public void testAddAdmin_Fails() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        when(adminDao.save(any(Admin.class))).thenThrow(new RuntimeException("Error while adding admin"));
        assertThrows(RuntimeException.class, () -> adminServiceImpl.addAdmin(admin));
        verify(adminDao, times(1)).save(any(Admin.class));
        verify(adminDao, times(0)).findById(any(Long.class));
        verify(adminDao, times(0)).findAll();
    }

    @Test
    public void testGetAllAdmins() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        Admin admin2 = new Admin(2L, "adminUsername2", "adminPassword2", "adminEmail2", null, null, null);
        when(adminDao.findAll()).thenReturn(Arrays.asList(admin, admin2));
        List<Admin> result = adminServiceImpl.getAllAdmins();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(adminDao, times(1)).findAll();
        verify(adminDao, times(0)).save(any(Admin.class));
        verify(adminDao, times(0)).findById(any(Long.class));
    }

    @Test
    public void testGetAllAdmins_Fails() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        when(adminDao.findAll()).thenThrow(new RuntimeException("Error while retrieving admins"));
        assertThrows(RuntimeException.class, adminServiceImpl::getAllAdmins);
        verify(adminDao, times(1)).findAll();
        verify(adminDao, times(0)).save(any(Admin.class));
        verify(adminDao, times(0)).findById(any(Long.class));
    }

    @Test
    public void testGetAdminById() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        when(adminDao.findById(1L)).thenReturn(admin);
        Admin result = adminServiceImpl.getAdminById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getAdminId());
        verify(adminDao, times(1)).findById(1L);
        verify(adminDao, times(0)).save(any(Admin.class));
        verify(adminDao, times(0)).findAll();
    }

    @Test
    public void testGetAdminById_Fails() {
        Admin admin = new Admin(1L, "adminUsername", "adminPassword", "adminEmail", null, null, null);
        when(adminDao.findById(1L)).thenThrow(new RuntimeException("Error while retrieving admin with ID"));
        assertThrows(RuntimeException.class, () -> adminServiceImpl.getAdminById(1L));
        verify(adminDao, times(1)).findById(1L);
        verify(adminDao, times(0)).save(any(Admin.class));
        verify(adminDao, times(0)).findAll();
    }
}