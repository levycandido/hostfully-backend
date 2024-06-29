package com.hostfully.factory;

import com.hostfully.entity.*;

public class PersonFactory {
    public static Person createPerson(String type, Long id, String name, Customer customer) {
        switch (type.toLowerCase()) {
            case "guest":
                return new Guest(id, name, customer);
            case "employee":
                return new Employee(id, name, customer);
            default:
                throw new IllegalArgumentException("Unknown person type: " + type);
        }
    }
}
