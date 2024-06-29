package com.hostfully.service.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
}
