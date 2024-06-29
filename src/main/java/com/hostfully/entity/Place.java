package com.hostfully.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode()
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Double Latitude;

    @Column(nullable = false)
    private Double Longitude;

    public Place(long l, String testProperty, String testLocation) {
         this.id = l;
         this.name = testProperty;
         this.street = testLocation;
    }
}
