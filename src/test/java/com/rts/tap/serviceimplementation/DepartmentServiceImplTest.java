package com.rts.tap.serviceimplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rts.tap.dao.DepartmentDao;
import com.rts.tap.model.Department;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentDao departmentDaoMock;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDepartmentSuccess() {
        Department department = new Department();
        department.setDepartmentName("HR");
        department.setDescription("Human Resources");

        // No exception should be thrown, and the save method should be called once
        departmentService.addDepartment(department);
        verify(departmentDaoMock, times(1)).save(department);
    }

    @Test
    void testAddDepartmentFailure() {
        Department department = new Department();
        department.setDepartmentName("HR");
        department.setDescription("Human Resources");

        // Mocking the exception to be thrown when save is called
        doThrow(new RuntimeException("Database error")).when(departmentDaoMock).save(any());

        // Expectation of exception
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            departmentService.addDepartment(department);
        });

        // Verify that the error message is as expected
        assertEquals("Failed to add department: Database error", thrown.getMessage());
        verify(departmentDaoMock, times(1)).save(department);
    }

    @Test
    void testGetAllDepartmentsSuccess() {
        Department department1 = new Department();
        department1.setDepartmentId(1L);
        department1.setDepartmentName("Finance");

        Department department2 = new Department();
        department2.setDepartmentId(2L);
        department2.setDepartmentName("HR");

        // Mocking the response
        when(departmentDaoMock.getAllDepartments()).thenReturn(List.of(department1, department2));

        List<Department> departments = departmentService.getAllDepartments();
        assertEquals(2, departments.size());
        verify(departmentDaoMock, times(1)).getAllDepartments();
    }

    @Test
    void testGetAllDepartmentsFailure() {
        when(departmentDaoMock.getAllDepartments()).thenThrow(new RuntimeException("Error fetching departments"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            departmentService.getAllDepartments();
        });

        assertEquals("Failed to retrieve all departments: Error fetching departments", thrown.getMessage());
        verify(departmentDaoMock, times(1)).getAllDepartments();
    }

    @Test
    void testUpdateDepartmentSuccess() {
        Department department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("Finance");
        department.setDescription("Finance Department");

        // No exception should be thrown, and the update method should be called once
        departmentService.updateDepartment(department);
        verify(departmentDaoMock, times(1)).update(department);
    }

    @Test
    void testUpdateDepartmentFailure() {
        Department department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("Finance");
        department.setDescription("Finance Department");

        // Mocking the exception to be thrown when update is called
        doThrow(new RuntimeException("Database error")).when(departmentDaoMock).update(any());

        // Expectation of exception
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            departmentService.updateDepartment(department);
        });

        // Verify that the error message is as expected
        assertEquals("Failed to update department: Database error", thrown.getMessage());
        verify(departmentDaoMock, times(1)).update(department);
    }

    @Test
    void testGetDepartmentByIdSuccess() {
        Long id = 1L;
        Department department = new Department();
        department.setDepartmentId(id);
        department.setDepartmentName("Finance");

        when(departmentDaoMock.findById(id)).thenReturn(Optional.of(department));

        Department retrievedDepartment = departmentService.getDepartmentById(id);
        assertNotNull(retrievedDepartment);
        assertEquals("Finance", retrievedDepartment.getDepartmentName());
        verify(departmentDaoMock, times(1)).findById(id);
    }

    @Test
    void testGetDepartmentByIdFailure() {
        Long id = 1L;
        when(departmentDaoMock.findById(id)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            departmentService.getDepartmentById(id);
        });

        assertEquals("Department not found with ID: " + id, thrown.getMessage());
        verify(departmentDaoMock, times(1)).findById(id);
    }
    
    
}