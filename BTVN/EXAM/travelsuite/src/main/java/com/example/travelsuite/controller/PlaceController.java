package com.example.travelsuite.controller;

import com.example.travelsuite.model.Place;
import com.example.travelsuite.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<?> addPlace(@RequestBody Place place) {
        return placeService.addPlace(place);
    }

    @GetMapping
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaceById(@PathVariable Long id) {
        return placeService.getPlaceById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlace(@PathVariable Long id, @RequestBody Place place) {
        return placeService.updatePlace(id, place);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlace(@PathVariable Long id) {
        return placeService.deletePlace(id);
    }
}
