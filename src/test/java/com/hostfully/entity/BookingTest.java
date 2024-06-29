package com.hostfully.entity;

import com.hostfully.service.dao.BookingStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    @Test
    public void testBookingCreation() {
        Person guest = new Guest(1L, "John Doe");
        Place property = new Place(1L, "Test Property", "Test Location");
        Booking booking = new Booking(1L, LocalDate.now(), LocalDate.now().plusDays(1), BookingStatus.PENDING, guest, property);

        assertNotNull(booking);
        assertEquals(1L, booking.getId());
        assertEquals(guest, booking.getGuest());
        assertEquals(property, booking.getProperty());
        assertEquals(LocalDate.now(), booking.getStartDate());
        assertEquals(LocalDate.now().plusDays(1), booking.getEndDate());
        assertEquals(BookingStatus.PENDING, booking.getBookingStatus());
    }

    @Test
    public void testBookingStatusUpdate() {
        Person guest = new Guest(1L, "John Doe");
        Place property = new Place(1L, "Test Property", "Test Location");
        Booking booking = new Booking(1L, LocalDate.now(), LocalDate.now().plusDays(1), BookingStatus.PENDING, guest, property);

        booking.setBookingStatus(BookingStatus.BOOKED);

        assertEquals(BookingStatus.BOOKED, booking.getBookingStatus());
    }
}
