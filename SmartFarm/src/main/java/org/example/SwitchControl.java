package org.example;
import com.pi4j.io.gpio.*;

public class SwitchControl {

    public static void main(String[] args) {
        // GPIO 핀 설정
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput switchPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_UP);

        // 스위치 입력 처리
        while (true) {
            if (switchPin.isLow()) {
                System.out.println("스위치가 눌렸습니다.");
            } else {
                System.out.println("스위치가 눌리지 않았습니다.");
            }
        }
    }
}