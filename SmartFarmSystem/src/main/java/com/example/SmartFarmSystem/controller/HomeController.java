package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.service.SmartFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final SmartFarmService smartFarmService;

    @Autowired
    public HomeController(SmartFarmService smartFarmService) {
        this.smartFarmService = smartFarmService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
