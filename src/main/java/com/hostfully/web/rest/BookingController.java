package com.hostfully.web.rest;

import com.hostfully.entity.Booking;
import com.hostfully.service.BookingService;
import com.hostfully.service.dao.BookingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(createdBooking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBookingDTO = bookingService.updateBooking(id, bookingDTO);
        return ResponseEntity.ok(updatedBookingDTO);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/rebook")
    public ResponseEntity<BookingDTO> rebook(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.rebook(id);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.getBooking(id);
        return ResponseEntity.ok(bookingDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBooking() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByPerson(@PathVariable Long personId) {
        List<BookingDTO> bookingsDtos = bookingService.findBookingsByPersonId(personId);
        if (bookingsDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookingsDtos);
    }
}
