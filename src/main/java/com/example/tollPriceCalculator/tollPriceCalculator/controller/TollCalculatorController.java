package com.example.tollPriceCalculator.tollPriceCalculator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tollPriceCalculator.tollPriceCalculator.service.TollGuruService;

@RestController
public class TollCalculatorController {

    @Autowired
    private TollGuruService tollGuruService;

    @GetMapping("/calculate-toll")
    public String calculateToll(@RequestParam String from, @RequestParam String to, @RequestParam String waypoint) {
        try {
            return tollGuruService.calculateToll(from, to, waypoint);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error calculating toll: " + e.getMessage();
        }
    }
}