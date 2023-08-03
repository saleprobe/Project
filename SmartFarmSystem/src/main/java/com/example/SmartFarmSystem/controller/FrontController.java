package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.service.SmartFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class FrontController {
    @Autowired
    private final SmartFarmService smartFarmService;

    public FrontController(SmartFarmService smartFarmService) {
        this.smartFarmService = smartFarmService;
    }

    @PostMapping("/react")
    @ResponseBody
    public SmartFarm react(@RequestBody SmartFarm smartFarm) throws Exception {
        System.out.println("Received JSON data: " + smartFarm);
        smartFarmService.saveMemo(smartFarm);

        return smartFarmService.getLastSmartFarm();
    }
}
