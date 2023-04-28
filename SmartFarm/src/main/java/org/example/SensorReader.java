package org.example;

import com.pi4j.io.gpio.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SensorReader {
    public static void main(String[] args) {
        // GPIO 핀 설정
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput sensorPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);

        // REST API 호출을 위한 RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // 센서값 읽어오기
        while (true) {
            if (sensorPin.isHigh()) {
                System.out.println("센서가 감지되었습니다.");

                try {
                    String url = "http://192.168.1.7:7420/api/sensor";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> entity = new HttpEntity<>("{\"sensorStatus\":\"ON\"}", headers);
                    ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                    System.out.println(response.getBody());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else {
                System.out.println("센서가 감지되지 않았습니다.");

                try {
                    String url = "http://192.168.1.7:7420/api/sensor";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> entity = new HttpEntity<>("{\"sensorStatus\":\"OFF\"}", headers);
                    ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                    System.out.println(response.getBody());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}