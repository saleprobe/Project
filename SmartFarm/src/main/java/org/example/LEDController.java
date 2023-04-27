package org.example;
import com.pi4j.io.gpio.*;

public class LEDController {
    public static void main(String[] args) {
        // GPIO 핀 설정
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED", PinState.LOW);

        // LED On/Off
        while (true) {
            ledPin.high();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ledPin.low();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
