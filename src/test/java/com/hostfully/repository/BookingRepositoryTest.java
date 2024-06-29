package com.hostfully.repository;

import com.hostfully.entity.Booking;
import com.hostfully.entity.Guest;
import com.hostfully.entity.Place;
import com.hostfully.service.dao.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlaceRepository propertyRepository;

    private Guest guest;
    private Place property;

    @BeforeEach
    public void setUp() {
        guest = new Guest();
        guest.setName("John Doe");
        guest = personRepository.save(guest);

        property = new Place();
        property.setName("Test Property");
        property.setLocation("Test Location");
        property = propertyRepository.save(property);
    }

    @Test
    public void testSaveBooking() {
        Booking booking = new Booking();
        booking.setStartDate(LocalDate.now());
        booking.setEndDate(LocalDate.now().plusDays(1));
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setGuest(guest);
        booking.setBookingStatus(property);

        Booking savedBooking = bookingRepository.save(booking);

        assertNotNull(savedBooking);
        assertNotNull(savedBooking.getId());
        assertEquals(guest, savedBooking.getGuest());
        assertEquals(property, savedBooking.getProperty());
    }

    @Test
    public void testFindOverlappingBookingsFail() {
        Booking booking1 = new Booking();
        booking1.setStartDate(LocalDate.now());
        booking1.setEndDate(LocalDate.now().plusDays(2));
        booking1.setBookingStatus(BookingStatus.PENDING);
        booking1.setGuest(guest);
        booking1.setProperty(property);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setEndDate(LocalDate.now().plusDays(3));
        booking2.setBookingStatus(BookingStatus.PENDING);
        booking2.setGuest(guest);
        booking2.setProperty(property);

        assertThrows(DataIntegrityViolationException.class, () -> {
            bookingRepository.save(booking2);
        });
    }

    @Test
    public void testFindAllBookings() {
        Booking booking = new Booking();
        booking.setStartDate(LocalDate.now());
        booking.setEndDate(LocalDate.now().plusDays(1));
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setGuest(guest);
        booking.setProperty(property);
        bookingRepository.save(booking);

        List<Booking> bookings = bookingRepository.findAll();

        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        assertEquals(guest, bookings.get(0).getGuest());
        assertEquals(property, bookings.get(0).getProperty());
    }
}
