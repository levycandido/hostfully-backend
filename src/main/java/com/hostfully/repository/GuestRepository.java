package com.hostfully.repository;

import com.hostfully.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    boolean existsByCustomerEmail(String email);
}
