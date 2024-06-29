package com.hostfully.repository;

import com.hostfully.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    Optional<Block> findByPlaceIdAndStartDateAndEndDate(Long placeId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT b FROM Block b WHERE b.startDate < :endDate AND b.endDate > :startDate")
    List<Block> findOverlappingBlocks(LocalDate startDate, LocalDate endDate);

}
