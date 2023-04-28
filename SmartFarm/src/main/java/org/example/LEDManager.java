package org.example;
import com.pi4j.io.gpio.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class LEDManager {
    public static void main(String[] args) {
        // GPIO 핀 설정
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED", PinState.LOW);

        // REST API 호출을 위한 RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        // LED On/Off
        while (true) {
            ledPin.high();
            try {
                // 1초 대기 후 서버로 LED 상태를 전송
                Thread.sleep(1000);
                String url = "http://192.168.1.7:7420/api/led";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>("{\"ledStatus\":\"ON\"}", headers);
                ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                System.out.println(response.getBody());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ledPin.low();
            try {
                // 1초 대기 후 서버로 LED 상태를 전송
                Thread.sleep(1000);
                String url = "http://192.168.1.7:7420/api/led";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>("{\"ledStatus\":\"OFF\"}", headers);
                ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                System.out.println(response.getBody());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}