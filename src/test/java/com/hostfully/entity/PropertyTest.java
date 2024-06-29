package com.hostfully.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PropertyTest {

    @Test
    public void testPropertyCreation() {
        Place property = new Place(1L, "Test Property", "Test Location");

        assertNotNull(property);
        assertEquals(1L, property.getId());
        assertEquals("Test Property", property.getName());
        assertEquals("Test Location", property.getStreet());
    }
}
