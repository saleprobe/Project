package com.example.SmartFarmSystem.handler;

import com.example.SmartFarmSystem.domain.SmartFarm;
import com.example.SmartFarmSystem.service.SmartFarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class RaspberryPiWebSocketHandler extends TextWebSocketHandler {

    private final SmartFarmService smartFarmService;

    @Autowired
    public RaspberryPiWebSocketHandler(SmartFarmService smartFarmService) {
        this.smartFarmService = smartFarmService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // MySQL 데이터베이스에서 최근 튜플을 조회하고 JSON으로 변환하여 클라이언트에게 전송
        SmartFarm latestSmartFarm = smartFarmService.getLastSmartFarm(); // 최근 튜플 조회하는 메소드 예시
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(latestSmartFarm);
        session.sendMessage(new TextMessage(json));
        System.out.println("INFO  Socket Request checked(from Pi)");

    }
}
