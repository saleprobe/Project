package com.example.SmartFarmSystem;

import com.example.SmartFarmSystem.service.SmartFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private final RaspberryPiWebSocketHandler raspberryPiWebSocketHandler;

    @Autowired
    private final ReactWebSocketHandler reactWebSocketHandler;

    public WebSocketConfig(RaspberryPiWebSocketHandler raspberryPiWebSocketHandler, ReactWebSocketHandler reactWebSocketHandler) {
        this.raspberryPiWebSocketHandler = raspberryPiWebSocketHandler;
        this.reactWebSocketHandler = reactWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(raspberryPiWebSocketHandler, "/raspberrypi-websocket").setAllowedOrigins("*");
        registry.addHandler(reactWebSocketHandler, "/react-websocket").setAllowedOrigins("*");
    }

}



