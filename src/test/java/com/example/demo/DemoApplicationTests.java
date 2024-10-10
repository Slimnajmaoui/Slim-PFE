package com.example.demo;

import com.example.demo.controller.AbsenceController;
import com.example.demo.controller.AdminController;
import com.example.demo.model.Absence;
import com.example.demo.model.Admin;
import com.example.demo.repository.AbsenceRepository;
import com.example.demo.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class DemoApplicationTests {

    @Mock
    private AbsenceRepository absenceRepository; // Mock de la dépendance AbsenceRepository

    @InjectMocks
    private AbsenceController absenceController; // Injection du mock dans le contrôleur Absence

    @Mock
    private AdminRepository adminRepository; // Mock de la dépendance AdminRepository

    @InjectMocks
    private AdminController adminController; // Injection du mock dans le contrôleur Admin

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialiser les mocks
    }

    @Test
    void contextLoads() {
        assertThat(absenceController).isNotNull(); // Vérifie que le contrôleur Absence est bien instancié
        assertThat(adminController).isNotNull(); // Vérifie que le contrôleur Admin est bien instancié
    }

    // Tests pour AbsenceController
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
        assertThat(absences.get(0).gettitre()).isEqualTo("Titre 1");
    }

    @Test
    void testGetAbsenceById() {
        Absence absence = new Absence("Titre 1", "Description 1", "En attente");
        absence.setId("1"); // Définir un ID pour l'absence

        when(absenceRepository.findById("1")).thenReturn(Optional.of(absence));

        ResponseEntity<Absence> response = absenceController.getAbsenceById("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().gettitre()).isEqualTo("Titre 1");
    }

    @Test
    void testGetAbsenceByIdNotFound() {
        when(absenceRepository.findById("1")).thenReturn(Optional.empty());

        ResponseEntity<Absence> response = absenceController.getAbsenceById("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    // Tests pour AdminController
    @Test
    void testGetAllAdmins() {
        // Simuler le comportement du repository
        Admin admin = new Admin("adminUser", "password", "admin@example.com");
        when(adminRepository.findAll()).thenReturn(Collections.singletonList(admin));

        // Appeler la méthode du contrôleur
        ResponseEntity<List<Admin>> response = adminController.getAllAdmins(null);
        List<Admin> admins = response.getBody();

        // Vérifier que le résultat est correct
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(admins).isNotNull();
        assertThat(admins).hasSize(1);
        assertThat(admins.get(0).getusername()).isEqualTo("adminUser");
    }

    @Test
    void testGetAdminById() {
        Admin admin = new Admin("adminUser", "password", "admin@example.com");
        admin.setId("1"); // Définir un ID pour l'admin

        when(adminRepository.findById("1")).thenReturn(Optional.of(admin));

        ResponseEntity<Admin> response = adminController.getAdminById("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getusername()).isEqualTo("adminUser");
    }

    @Test
    void testGetAdminByIdNotFound() {
        when(adminRepository.findById("1")).thenReturn(Optional.empty());

        ResponseEntity<Admin> response = adminController.getAdminById("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testCreateAdmin() {
        Admin admin = new Admin("adminUser", "password", "admin@example.com");

        when(adminRepository.save(admin)).thenReturn(admin);

        ResponseEntity<Admin> response = adminController.createAdmin(admin);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getusername()).isEqualTo("adminUser");
    }

    @Test
    void testUpdateAdmin() {
        Admin admin = new Admin("adminUser", "password", "admin@example.com");
        admin.setId("1"); // Définir un ID pour l'admin

        when(adminRepository.findById("1")).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        ResponseEntity<Admin> response = adminController.updateAdmin("1", admin);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getusername()).isEqualTo("adminUser");
    }

    @Test
    void testDeleteAdmin() {
        Admin admin = new Admin("adminUser", "password", "admin@example.com");
        admin.setId("1"); // Définir un ID pour l'admin

        when(adminRepository.findById("1")).thenReturn(Optional.of(admin));

        ResponseEntity<HttpStatus> response = adminController.deleteAdmin("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testDeleteAllAdmins() {
        ResponseEntity<HttpStatus> response = adminController.deleteAllAdmins();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
