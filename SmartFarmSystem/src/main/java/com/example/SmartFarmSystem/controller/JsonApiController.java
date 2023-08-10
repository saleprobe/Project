package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.service.SmartFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController
//@RequestMapping("/api")
public class JsonApiController {

    private final SmartFarmService smartFarmService;

    @Autowired
    public JsonApiController(SmartFarmService memberService) {
        this.smartFarmService = memberService;
    }

    @GetMapping("/data")
    @ResponseBody
    public SmartFarm getdata_g(){
        System.out.println("Get Request checked");
        return smartFarmService.getLastSmartFarm();
    }

    @PostMapping("/data")
    @ResponseBody
    public SmartFarm getdata_p(){
        System.out.println("Post Request checked");
        return smartFarmService.getLastSmartFarm();
    }
}
