package com.hostfully.service.dao;

import com.hostfully.entity.GuestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private Long id;
    private GuestDTO guest;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
}
