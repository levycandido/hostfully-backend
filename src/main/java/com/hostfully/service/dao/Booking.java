package com.hostfully.service.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private Long id;
    private String guestName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
}
