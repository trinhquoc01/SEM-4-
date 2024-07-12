package com.example.travelsuite.repository;


import com.example.travelsuite.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends    JpaRepository<Image, Long> {
}