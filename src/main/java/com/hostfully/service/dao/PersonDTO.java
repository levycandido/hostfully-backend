package com.hostfully.service.dao;

import com.hostfully.entity.CustomerDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode()
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;
    private String name;
    private CustomerDTO customer;

    @Column(nullable = false)
    private String type;
}
