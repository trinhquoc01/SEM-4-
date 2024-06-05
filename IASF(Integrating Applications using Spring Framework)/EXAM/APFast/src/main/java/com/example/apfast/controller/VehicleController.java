package com.example.apfast.controller;

import com.example.apfast.entity.Vehicle;
import com.example.apfast.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String getAllVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "vehicles";
    }

    @GetMapping("/new")
    public String showAddVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "add-vehicle";
    }

    @PostMapping
    public String saveVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/edit/{id}")
    public String showEditVehicleForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id).orElseThrow(() -> new IllegalArgumentException("Invalid vehicle Id:" + id));
        model.addAttribute("vehicle", vehicle);
        return "edit-vehicle";
    }

    @PostMapping("/update/{id}")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute Vehicle vehicle) {
        vehicle.setId(id);
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }
}