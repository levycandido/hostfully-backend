package com.hostfully.service;

import com.hostfully.entity.Place;
import com.hostfully.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }

    public Place getPlace(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            return place.get();
        } else {
            throw new IllegalArgumentException("Place not found");
        }
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Place updatePlace(Long id, Place place) {
        if (!placeRepository.existsById(id)) {
            throw new IllegalArgumentException("Place not found");
        }
        place.setId(id);
        return placeRepository.save(place);
    }

    public void deletePlace(Long id) {
        if (!placeRepository.existsById(id)) {
            throw new IllegalArgumentException("Place not found");
        }
        placeRepository.deleteById(id);
    }
}
