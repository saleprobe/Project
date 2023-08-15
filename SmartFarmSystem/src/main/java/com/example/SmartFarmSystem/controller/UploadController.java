package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.service.SmartFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UploadController {

    private final SmartFarmService smartFarmService;

    @Autowired
    public UploadController(SmartFarmService memberService) {
        this.smartFarmService = memberService;
    }

    @PostMapping("/upload")
    @ResponseBody
    public void handleFileUpload(@RequestParam("jsonFile") MultipartFile file) throws Exception {
        System.out.println("Received file: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize() + " bytes");

        // 파일 처리 로직
        ObjectMapper objectMapper = new ObjectMapper();
        SmartFarm smartFarm = objectMapper.readValue(file.getInputStream(), SmartFarm.class);

        // 데이터베이스에 Json 데이터를 처리하고 저장
        smartFarmService.join(smartFarm);
    }

    @PostMapping("/")
    @ResponseBody
    public void handleFileUpload(@RequestBody SmartFarm smartFarm) throws Exception {
        System.out.println("Received JSON data: " + smartFarm);

        // 데이터베이스에 Json 데이터를 처리하고 저장
        smartFarmService.join(smartFarm);
    }

}
