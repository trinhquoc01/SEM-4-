package com.example.travelsuite.repository;


import com.example.travelsuite.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}