package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.service.SmartFarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/smartfarm_related")
public class SmartFarmController {

    // SmartFarmService를 주입
    private final SmartFarmService smartFarmService;

    public SmartFarmController(SmartFarmService smartFarmService) {
        this.smartFarmService = smartFarmService;
    }

    @GetMapping("/check_able_sf_id")
    public ResponseEntity<String> checkUserCode(@RequestParam("user_sf_id") int user_sf_id) {
        // SmartFarm 테이블에서 user_sf_id와 일치하는 sf_id를 찾음
        SmartFarm smartFarm = smartFarmService.findBySfId(user_sf_id);

        if (smartFarm != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("스마트팜 고유번호 등록 가능");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("스마트팜 고유번호 등록 불가능");
        }
    }
}