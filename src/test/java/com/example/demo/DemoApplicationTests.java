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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DemoApplicationTests {

    @Mock
    private AbsenceRepository absenceRepository;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AbsenceController absenceController;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
        assertThat(absenceController).isNotNull();
        assertThat(adminController).isNotNull();
    }

    // Tests pour AbsenceController
    @Test
    void testGetAllAbsences() {
        Absence absence = new Absence("Titre 1", "description 1", "En attente");
        when(absenceRepository.findAll()).thenReturn(Collections.singletonList(absence));

        ResponseEntity<List<Absence>> response = absenceController.getAllAbsences(null);
        List<Absence> absences = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(absences).isNotNull();
        assertThat(absences).hasSize(1);
        assertThat(absences.get(0).gettitre()).isEqualTo("Titre 1");
    }

    @Test
    void testGetAbsenceById() {
        Absence absence = new Absence("Titre 1", "description 1", "En attente");
        absence.setId("1");

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
        Admin admin = new Admin("admin", "password", "admin@example.com");
        when(adminRepository.findAll()).thenReturn(Collections.singletonList(admin));

        ResponseEntity<List<Admin>> response = adminController.getAllAdmins(null);
        List<Admin> admins = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(admins).isNotNull();
        assertThat(admins).hasSize(1);
        assertThat(admins.get(0).getusername()).isEqualTo("admin");
    }

    @Test
    void testGetAdminById() {
        Admin admin = new Admin("admin", "motdepasse", "admin@example.com");
        admin.setId("1");

        when(adminRepository.findById("1")).thenReturn(Optional.of(admin));

        ResponseEntity<Admin> response = adminController.getAdminById("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getusername()).isEqualTo("admin");
    }

    @Test
    void testGetAdminByIdNotFound() {
        when(adminRepository.findById("1")).thenReturn(Optional.empty());

        ResponseEntity<Admin> response = adminController.getAdminById("1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

  @Test
void testCreateAdmin() {
    // Créer un Admin
    Admin admin = new Admin();
    admin.setusername("testUser");
    admin.setemail("test@example.com");
    admin.setmotdepasse("password");

    // Simuler le comportement du repository
    when(adminRepository.save(any(Admin.class))).thenReturn(admin);

    // Appeler la méthode à tester
    ResponseEntity<Admin> response = adminController.createAdmin(admin); // Capturer la réponse ici

    // Vérifiez que la réponse n'est pas nulle
    assertThat(response).isNotNull(); // Vérifier que la réponse elle-même n'est pas nulle
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED); // Vérifier le statut de la réponse
    assertThat(response.getBody()).isNotNull(); // Vérifier que le corps de la réponse n'est pas nul
    assertThat(response.getBody().getusername()).isEqualTo("testUser"); // Vérifier que le nom d'utilisateur est correct
}

}
