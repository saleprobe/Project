package com.example.SmartFarmSystem.controller;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.service.SmartFarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Controller
public class WebSocketController {

    private final SmartFarmService smartFarmService;
    private final ObjectMapper objectMapper;

    @Autowired
    public WebSocketController(SmartFarmService smartFarmService, ObjectMapper objectMapper) {
        this.smartFarmService = smartFarmService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/react-websocket-json")
    @ResponseBody
    public void receiveWebSocketData(@RequestBody String jsonData, WebSocketSession session) throws IOException {
        System.out.println("INFO receive WebSocket Data");
        // JSON 데이터 파싱
        SmartFarm smartFarm = objectMapper.readValue(jsonData, SmartFarm.class);

        // MySQL 데이터베이스에 저장
        smartFarmService.join(smartFarm);

        // 저장된 데이터를 다시 클라이언트로 전송
        String responseJson = objectMapper.writeValueAsString(smartFarm);
        TextMessage responseMessage = new TextMessage(responseJson);
        session.sendMessage(responseMessage);
    }
}
