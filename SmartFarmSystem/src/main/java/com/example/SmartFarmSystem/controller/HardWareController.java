//package com.example.SmartFarmSystem.controller;
//
//import com.example.SmartFarmSystem.domain.SmartFarm;
//import com.example.SmartFarmSystem.service.SmartFarmService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.time.LocalDateTime;
//
//@Controller
//public class HardWareController {
//
//    @Autowired
//    private final SmartFarmService smartFarmService;
//
//    public HardWareController(SmartFarmService smartFarmService) {
//        this.smartFarmService = smartFarmService;
//    }
//
//    @GetMapping("/raspberrypi")
//    @ResponseBody
//    public SmartFarm getdata_r(){
//        LocalDateTime currentTime = LocalDateTime.now();
//        System.out.println("Get Request checked(/raspberrypi) " + currentTime);
//        return smartFarmService.getLastSmartFarm();
//    }
//
//    @PostMapping("/raspberrypi")
//    @ResponseBody
//    public void savedata_r(@RequestBody SmartFarm smartFarm) throws Exception {
//        LocalDateTime currentTime = LocalDateTime.now();
//        System.out.println("Received JSON data: " + smartFarm + " from raspberrypi " + currentTime);
//
//        // 데이터베이스에 Json 데이터를 처리하고 저장
//        smartFarmService.join(smartFarm);
//    }
//}
