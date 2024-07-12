package com.example.travelsuite.service;

import com.example.travelsuite.model.Place;
import com.example.travelsuite.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public ResponseEntity<?> addPlace(Place place) {
        placeRepository.save(place);
        return ResponseEntity.ok("Place added successfully!");
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public ResponseEntity<?> getPlaceById(Long id) {
        Place place = placeRepository.findById(id).orElse(null);
        if (place == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(place);
    }

    public ResponseEntity<?> updatePlace(Long id, Place place) {
        Place existingPlace = placeRepository.findById(id).orElse(null);
        if (existingPlace == null) {
            return ResponseEntity.notFound().build();
        }
        existingPlace.setName(place.getName());
        existingPlace.setDescription(place.getDescription());
        existingPlace.setLocation(place.getLocation());
        placeRepository.save(existingPlace);
        return ResponseEntity.ok("Place updated successfully!");
    }

    public ResponseEntity<?> deletePlace(Long id) {
        if (!placeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        placeRepository.deleteById(id);
        return ResponseEntity.ok("Place deleted successfully!");
    }
}
