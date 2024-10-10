package com.example.demo;

import com.example.demo.controller.AbsenceController;
import com.example.demo.model.Absence;
import com.example.demo.repository.AbsenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AbsenceControllerTests {

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
        ResponseEntity<List<Absence>> response = absenceController.getAllAbsences(null);
        List<Absence> absences = response.getBody();

        // Vérifier que le résultat est correct
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(absences).isNotNull();
        assertThat(absences).hasSize(1);
        assertThat(absences.get(0).getTitre()).isEqualTo("Titre 1");
    }

    @Test
    void testGetAbsenceById() {
        Absence absence = new Absence("Titre 1", "Description 1", "En attente");
        absence.setId("1"); // Définir un ID pour l'absence

        when(absenceRepository.findById("1")).thenReturn(Optional.of(absence));

        ResponseEntity<Absence> response = absenceController.getAbsenceById("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitre()).isEqualTo("Titre 1");
    }

    @Test
    void testGetAbsenceByIdNotFound() {
        when(absenceRepository.findById("1")).thenReturn(Optional.empty());

        ResponseEntity<Absence> response = absenceController.getAbsenceById("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testCreateAbsence() {
        Absence absence = new Absence("Titre 1", "Description 1", "En
