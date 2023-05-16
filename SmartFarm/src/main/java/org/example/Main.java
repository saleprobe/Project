package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = {"org.example.LedStatusRepository"})
//@SpringBootApplication(scanBasePackageClasses = Main.class)
//@ComponentScan(basePackages = {"org.example.LEDController"})
//@ComponentScan(basePackages = {"org.example.LedStatusRepository"})
@SpringBootApplication
@ComponentScan(basePackages = {"org.example.LedStatusRepository"})
public class Main {

    @Autowired
    private static LEDController ledController;
//    @Autowired
//    public LEDController(LedStatusRepository ledStatusRepository) {
//        this.ledStatusRepository = ledStatusRepository;
//    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.run(args);

        String fakeJson = "{\"ledStatus\": true}";
        try {
            ledController.setLEDStatus(fakeJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}