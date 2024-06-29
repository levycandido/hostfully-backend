package com.hostfully.config;

import com.hostfully.entity.Guest;
import com.hostfully.entity.Person;

public interface AuthService {
    Person createPerson(Person signupRequest);
}