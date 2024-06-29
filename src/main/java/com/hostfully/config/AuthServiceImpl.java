//package com.hostfully.config;
//
//import com.hostfully.entity.Guest;
//import com.hostfully.entity.Person;
//import com.hostfully.factory.PersonFactory;
//import com.hostfully.repository.GuestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//    private final GuestRepository guestRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthServiceImpl(GuestRepository customerRepository, PasswordEncoder passwordEncoder) {
//        this.guestRepository = customerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//    @Override
//    public Guest createPerson(Person signupRequest) {
//        //Check if customer already exist
//        if (guestRepository.existsByCustomerEmail(signupRequest.getCustomer().getEmail())) {
//            return null;
//        }
//
//        Guest guest = (Guest) PersonFactory.createPerson("guest",
//                null,
//                signupRequest.getName(),
//                signupRequest.getCustomer());
//
//        String hashPassword = passwordEncoder.encode(signupRequest.getCustomer().getPassword());
//        guest.getCustomer().setPassword(hashPassword);
//        Person createdCustomer = guestRepository.save(guest);
//        guest.setId(createdCustomer.getId());
//
//        return guest;
//    }
//
//}