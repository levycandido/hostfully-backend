package com.hostfully.repository;

import com.hostfully.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b " +
            "WHERE b.startDate < :endDate AND b.endDate > :startDate " +
            "AND b.status <> 'CANCELED' AND (:id IS NULL OR b.id <> :id)")
    List<Booking> findOverlappingBookings(LocalDate startDate, LocalDate endDate, Long id);

    List<Booking> findByGuestId(Long personId);

}
