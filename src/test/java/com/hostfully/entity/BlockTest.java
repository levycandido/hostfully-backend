package com.hostfully.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockTest {

    @Test
    public void testBlockCreation() {
        Place property = new Place(1L, "Test Property", "Test Location");
        Block block = new Block(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), property);
        assertEquals(1L, block.getId());
        assertEquals(LocalDate.of(2023, 7, 1), block.getStartDate());
        assertEquals(LocalDate.of(2023, 7, 7), block.getEndDate());
    }

    @Test
    public void testBlockEquals() {
        Place property1 = new Place(1L, "Test Property", "Test Location");
        Place property2 = new Place(1L, "Test Property2", "Test Location2");
        Block block1 = new Block(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), property1);
        Block block2 = new Block(1L, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 7), property2);
        assertEquals(block1, block2);
    }
}
