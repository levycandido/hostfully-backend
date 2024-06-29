package com.hostfully.entity;

import com.hostfully.service.dao.status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode()
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private status status;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Person guest;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;


}