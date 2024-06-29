package com.hostfully.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeTest {

    @Test
    public void testEmployeeCreation() {
        Employee employee = new Employee(1L, "Jane Doe",null);

        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertEquals("Jane Doe", employee.getName());
    }
}
