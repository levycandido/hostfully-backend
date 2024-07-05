package com.hostfully.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class Guest extends Person {

   public Guest(Long id, String name, Customer customer ) {
       super(id, name, customer, "guest");
    }

}
