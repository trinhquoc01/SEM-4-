package com.example.travelsuite.repository;

import com.example.travelsuite.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}