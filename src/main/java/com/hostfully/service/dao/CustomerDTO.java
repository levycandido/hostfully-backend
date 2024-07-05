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
public class CustomerDTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    private String email;
}