package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Component
public class LEDController {

    private final LedStatusRepository ledStatusRepository;

    @Autowired
    public LEDController(LedStatusRepository ledStatusRepository) {
        this.ledStatusRepository = ledStatusRepository;
    }

    @PostMapping("/led")
    public void setLEDStatus(@RequestBody String json) throws Exception {
        json = "{\"ledStatus\": true}";  // 실험용 json

        // JSON 파싱
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        boolean ledStatus = root.path("ledStatus").asBoolean();

        // DB에 저장
        LEDStatus status = new LEDStatus(ledStatus);
        ledStatusRepository.save(status);

        System.out.println(json);
    }

}