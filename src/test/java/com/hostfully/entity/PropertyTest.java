package com.hostfully.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PropertyTest {

    @Test
    public void testPropertyCreation() {
        Place place = new Place(1L, "Test Property", "Test Location");

        assertNotNull(place);
        assertEquals(1L, place.getId());
        assertEquals("Test Property", place.getName());
        assertEquals("Test Location", place.getStreet());
    }
}
