package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.service.SmartFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
//@RestController
//@RequestMapping("/api")
public class SendController {

    private final SmartFarmService smartFarmService;

    @Autowired
    public SendController(SmartFarmService memberService) {
        this.smartFarmService = memberService;
    }

    @GetMapping("/data")
    @ResponseBody
    public SmartFarm getdata_g(){
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("INFO  Get Request checked " + currentTime);
        return smartFarmService.getLastSmartFarm();
    }

    @PostMapping("/data")
    @ResponseBody
    public SmartFarm getdata_p(){
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("INFO  Post Request checked " + currentTime);
        return smartFarmService.getLastSmartFarm();
    }
}
