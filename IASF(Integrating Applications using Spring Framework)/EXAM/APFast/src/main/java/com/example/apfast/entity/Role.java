package com.example.apfast.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Role {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;
    private String role;

}