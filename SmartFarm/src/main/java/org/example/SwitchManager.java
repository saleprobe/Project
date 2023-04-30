package org.example;

import com.pi4j.io.gpio.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SwitchManager {

    public static void main(String[] args) {
        // GPIO 핀 설정
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput switchPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_UP);

        // REST API 호출을 위한 RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // 스위치 입력 처리
        while (true) {
            if (switchPin.isLow()) {
                try {
                    // 스위치가 눌렸을 때 서버로 스위치 상태 전송
                    String url = "http://192.168.1.7:7420/api/switch";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> entity = new HttpEntity<>("{\"switchStatus\":\"PRESSED\"}", headers);
                    ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                    System.out.println(response.getBody());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    // 스위치가 눌리지 않았을 때 서버로 스위치 상태 전송
                    String url = "http://192.168.1.7:7420/api/switch";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> entity = new HttpEntity<>("{\"switchStatus\":\"RELEASED\"}", headers);
                    ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                    System.out.println(response.getBody());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}