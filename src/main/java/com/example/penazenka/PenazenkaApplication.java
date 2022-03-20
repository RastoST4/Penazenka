package com.example.penazenka;

import com.example.penazenka.service.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PenazenkaApplication implements CommandLineRunner {
    @Autowired
    private UserInput inputService;

    public static void main(String[] args) {
        SpringApplication.run(PenazenkaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        inputService.run();
    }
}
