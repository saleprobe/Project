package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.service.SmartFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class FrontController {
    @Autowired
    private final SmartFarmService smartFarmService;

    public FrontController(SmartFarmService smartFarmService) {
        this.smartFarmService = smartFarmService;
    }

    @PostMapping("/react")
    @ResponseBody
    public SmartFarm react(@RequestBody SmartFarm smartFarm) throws Exception {
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Post Request checked(/react) " + currentTime);
        smartFarmService.join(smartFarm);

        return smartFarmService.getLastSmartFarm();
    }
}
