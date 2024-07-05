package com.hostfully.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class GuestDTO extends Person {

   public GuestDTO(Long id, String name, Customer customer ) {
       super(id, name, customer, "guest");
    }

}
