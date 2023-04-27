package org.example;
import com.pi4j.io.gpio.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // GPIO 핀 설정
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput sensorPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);

        // 센서값 읽어오기
        while (true) {
            if (sensorPin.isHigh()) {
                System.out.println("센서가 감지되었습니다.");
            } else {
                System.out.println("센서가 감지되지 않았습니다.");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
