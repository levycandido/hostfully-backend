package com.hostfully.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GuestTest {

    @Test
    public void testGuestCreation() {
        Customer customer = new Customer(1L, "123", "levycandido@hotmail.com");
        Guest guest = new Guest(1L, "John Doe", customer);

        assertNotNull(guest);
        assertEquals(1L, guest.getId());
        assertEquals("John Doe", guest.getName());
    }
}
