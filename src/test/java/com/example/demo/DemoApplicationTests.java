package com.example.demo;

import com.example.demo.controller.AbsenceController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private AbsenceController absenceController; // On teste que ce contrôleur est bien injecté par Spring

    @Test
    void contextLoads() {
        assertThat(absenceController).isNotNull(); // Vérifie que le contrôleur est bien instancié
    }
}
