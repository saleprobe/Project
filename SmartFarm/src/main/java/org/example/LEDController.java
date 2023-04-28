package org.example;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/led")
public class LEDController {

    // 서버에서의 LED 상태
    private boolean isLedOn = false;

    @GetMapping("/status")
    public String getLEDStatus() {
        return isLedOn ? "LED is on" : "LED is off";
    }

    @PutMapping("/status")
    public void setLEDStatus(@RequestParam("status") boolean status) {
        isLedOn = status;
    }
}