package com.hostfully.service;

import com.hostfully.entity.Guest;
import com.hostfully.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public Guest createGuest(Guest person) {
        return guestRepository.save(person);
    }

    public Guest getGuest(Long id) {
        Optional<Guest> person = guestRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw new IllegalArgumentException("Guest not found");
        }
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest updateGuest(Long id, Guest person) {
        if (!guestRepository.existsById(id)) {
            throw new IllegalArgumentException("Guest not found");
        }
        person.setId(id);
        return guestRepository.save(person);
    }

    public void deleteGuest(Long id) {
        if (!guestRepository.existsById(id)) {
            throw new IllegalArgumentException("Guest not found");
        }
        guestRepository.deleteById(id);
    }
}
