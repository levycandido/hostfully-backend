package com.hostfully.service;

import com.hostfully.entity.Block;
import com.hostfully.entity.Booking;
import com.hostfully.repository.BlockRepository;
import com.hostfully.repository.BookingRepository;
import com.hostfully.service.dao.Status;
import com.hostfully.service.exception.BlockCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Transactional
    public Booking createBooking(Booking booking) {
        validateBookingDates(booking);
        Booking savedBooking = bookingRepository.save(booking);
        createBlock(booking);
        return savedBooking;
    }

    @Transactional
    public Booking updateBooking(Long id, Booking booking) {
        booking.setId(id);
        return bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<Booking> getAllBookings() {
        List<Booking> blocks = bookingRepository.findAll();
        if (blocks.isEmpty()) {
            throw new IllegalArgumentException("No blocks found");
        }
        return blocks;
    }

    public List<Booking> findBookingsByPersonId(Long personId) {
        return bookingRepository.findByGuestId(personId);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        Booking booking = optionalBooking.orElseThrow(() -> new RuntimeException("Booking not found"));

        Optional<Block> optionalBlock = blockRepository
                .findByPlaceIdAndStartDateAndEndDate(
                        booking.getPlace().getId(),
                        booking.getStartDate(),
                        booking.getEndDate());
        optionalBlock.ifPresent(blockRepository::delete);

        booking.setStatus(Status.CANCELED);
        bookingRepository.save(booking);
    }

    @Transactional
    public Booking rebook(Long id) {
        Optional<Booking> bookingOtp = bookingRepository.findById(id);
        if (bookingOtp.isPresent()) {
            Booking booking = bookingOtp.get();
            validateBookingDates(booking);
            booking.setStatus(Status.PENDING);
            createBlock(booking);
            return bookingRepository.save(booking);
        } else {
            throw new IllegalArgumentException("Block not found");
        }
    }

    private void createBlock(Booking booking) {
        try {
            Block block = new Block();
            block.setStartDate(booking.getStartDate());
            block.setEndDate(booking.getEndDate());
            block.setPlace(booking.getPlace());
            blockRepository.save(block);
        } catch (Exception e) {
            // Flogger.error("Error creating block for booking: {}", booking, e);
            throw new BlockCreationException("Failed to create block for booking: " + booking.getId(), e);
        }
    }

    @Transactional
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    private void validateBookingDates(Booking booking) {
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(
                booking.getStartDate(), booking.getEndDate(), booking.getId());
        if (!overlappingBookings.isEmpty()) {
            throw new RuntimeException("Booking dates overlap with existing bookings");
        }

        List<Block> overlappingBlocks = blockRepository.findOverlappingBlocks(
                booking.getStartDate(), booking.getEndDate());
        if (!overlappingBlocks.isEmpty()) {
            throw new RuntimeException("Booking dates overlap with existing blocks");
        }
    }
}
