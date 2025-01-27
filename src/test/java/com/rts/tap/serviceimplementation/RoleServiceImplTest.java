package com.rts.tap.serviceimplementation;

import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.RoleDao;
import com.rts.tap.model.Role;
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
public class RoleServiceImplTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    public void testAddRole() {
        // Given
        Role role = new Role();
        role.setRoleId(null); // New role, so ID should be null

        // When
        when(roleDao.save(any(Role.class))).thenAnswer(invocation -> (Role) invocation.getArguments()[0]);

        // Then
        String result = roleService.addRole(role);
        assertNotNull(result);
        verify(roleDao, times(1)).save(role);
    }

    @Test
    public void testAddRole_Fails() {
        // Given
        Role role = new Role();

        // When
        when(roleDao.save(any(Role.class))).thenThrow(new RuntimeException("Error while adding role"));

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> roleService.addRole(role));
        assertEquals(MessageConstants.FAILURE_MESSAGE, thrownException.getMessage());
        verify(roleDao, times(1)).save(role);
    }

    @Test
    public void testGetAllRole() {
        // Given
        Role role1 = new Role();
        role1.setRoleId(1L);
        role1.setRoleName("Role 1");

        Role role2 = new Role();
        role2.setRoleId(2L);
        role2.setRoleName("Role 2");

        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);

        // When
        when(roleDao.getAllRole()).thenReturn(roles);

        // Then
        List<Role> result = roleService.getAllRole();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Role 1", result.get(0).getRoleName());
        verify(roleDao, times(1)).getAllRole();
    }

    @Test
    public void testGetAllRole_Fails() {
        // When
        when(roleDao.getAllRole()).thenThrow(new RuntimeException("Error while fetching roles"));

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> roleService.getAllRole());
        assertEquals(MessageConstants.FAILURE_MESSAGE, thrownException.getMessage());
        verify(roleDao, times(1)).getAllRole();
    }

    @Test
    public void testUpdateRole() {
        // Given
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("Updated Role");

        // When
        when(roleDao.update(any(Role.class))).thenAnswer(invocation -> (Role) invocation.getArguments()[0]);

        // Then
        String result = roleService.updateRole(role);
        assertNotNull(result);
        verify(roleDao, times(1)).update(role);
    }

    @Test
    public void testUpdateRole_Fails() {
        // Given
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("Updated Role");

        // When
        doThrow(new RuntimeException("Error while updating role")).when(roleDao).update(any(Role.class));

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> roleService.updateRole(role));
        assertEquals(MessageConstants.FAILURE_MESSAGE, thrownException.getMessage());
        verify(roleDao, times(1)).update(role);
    }

    @Test
    public void testGetRoleById() {
        // Given
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("Role 1");

        // When
        when(roleDao.getRoleById(1L)).thenReturn(role);

        // Then
        Role result = roleService.getRoleById(1L);
        assertNotNull(result);
        assertEquals("Role 1", result.getRoleName());
        verify(roleDao, times(1)).getRoleById(1L);
    }

    @Test
    public void testGetRoleById_NotFound() {
        // When
        when(roleDao.getRoleById(1L)).thenReturn(null);

        // Then
        Role result = roleService.getRoleById(1L);
        assertNull(result);
        verify(roleDao, times(1)).getRoleById(1L);
    }

    @Test
    public void testGetRoleById_Fails() {
        // When
        when(roleDao.getRoleById(1L)).thenThrow(new RuntimeException("Error while fetching role"));

        // Then
        RuntimeException thrownException = assertThrows(RuntimeException.class, () -> roleService.getRoleById(1L));
        assertEquals(MessageConstants.FAILURE_MESSAGE, thrownException.getMessage());
        verify(roleDao, times(1)).getRoleById(1L);
    }
}