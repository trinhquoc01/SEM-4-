package com.example.apfast.controller;

import com.example.apfast.entity.Vehicle;
import com.example.apfast.service.MemberService;
import com.example.apfast.service.VehicleService;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String getAllVehicles(Model model, HttpSession session) {
        if (memberService.checkSession(session)){
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            return "vehicles";
        }else {
            return "redirect:/";
        }

    }


    @GetMapping("/new")
    public String getNewVehicle(Model model, HttpSession session) {
        if (memberService.checkSession(session)){
            model.addAttribute("vehicle", new Vehicle());
            return "vehicle-form";
        }else {
            return "redirect:/";
        }

    }

    @PostMapping
    public String saveNewVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("edit/{id}")
    public String getEditVehicle(@PathVariable Long id, Model model, HttpSession session) {
        if (memberService.checkSession(session)){
            Vehicle vehicle = vehicleService.getVehicleById(id);
            model.addAttribute("vehicle", vehicle);
            return "vehicle-form";
        }else {
            return "redirect:/";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id, Model model, HttpSession session) {
        if (memberService.checkSession(session)){
            vehicleService.deleteVehicle(id);
            return "redirect:/vehicles";
        }else {
            return "redirect:/";
        }

    }
}