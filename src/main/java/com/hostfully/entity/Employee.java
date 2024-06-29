package com.hostfully.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Employee extends Person {

    public Employee(Long id, String name, Customer customer ) {
        super(id, name, customer, "employee");
    }
}
