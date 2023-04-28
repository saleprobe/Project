package org.example;

import com.pi4j.io.gpio.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SwitchControl {
    public static void main(String[] args) {
        // GPIO 핀 설정
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput switchPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_UP);

        // REST API 호출을 위한 RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // 스위치 입력 처리
        while (true) {
            // 1초 대기 후 서버에서 스위치 상태를 조회
            try {
                Thread.sleep(1000);
                String url = "http://192.168.1.7:7420/api/switch";
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                String switchStatus = response.getBody();

                if (switchStatus.equals("ON")) {
                    System.out.println("스위치가 눌렸습니다.");
                } else if (switchStatus.equals("OFF")) {
                    System.out.println("스위치가 눌리지 않았습니다.");
                } else {
                    System.out.println("잘못된 스위치 상태입니다.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}