package com.hostfully.config;

import com.hostfully.entity.Person;
import com.hostfully.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public CustomerServiceImpl(PersonRepository customerRepository) {
        this.personRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByCustomerEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Person not found with email: " + email));

        return new User(person.getCustomer().getEmail(),
                person.getCustomer().getPassword(), Collections.emptyList());
    }
}