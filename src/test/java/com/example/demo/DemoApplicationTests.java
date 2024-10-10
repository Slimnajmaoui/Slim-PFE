package com.example.demo;

import com.example.demo.controller.AbsenceController;
import com.example.demo.model.Absence;
import com.example.demo.repository.AbsenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoApplicationTests {

    @Mock
    private AbsenceRepository absenceRepository; // Mock de la dépendance AbsenceRepository

    @InjectMocks
    private AbsenceController absenceController; // Injection du mock dans le contrôleur

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks
    }

    @Test
    void contextLoads() {
        assertThat(absenceController).isNotNull(); // Vérifie que le contrôleur est bien instancié
    }

    @Test
    void testGetAllAbsences() {
        // Simuler le comportement du repository
        Absence absence = new Absence("Titre 1", "Description 1", "En attente");
        when(absenceRepository.findAll()).thenReturn(Collections.singletonList(absence));

        // Appeler la méthode du contrôleur
        List<Absence> absences = absenceController.getAllAbsences(null).getBody();

        // Vérifier que le résultat est correct
        assertThat(absences).isNotNull();
        assertThat(absences).hasSize(1);
        assertThat(absences.get(0).gettitre()).isEqualTo("Titre 1");
    }
}

