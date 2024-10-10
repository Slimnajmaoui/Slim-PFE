package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Absence;
import com.example.demo.repository.AbsenceRepository;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AbsenceController {

  @Autowired
  AbsenceRepository absenceRepository;

  @GetMapping("/Absences")
  public ResponseEntity<List<Absence>> getAllAbsences(@RequestParam(required = false) String titre) {
    try {
      List<Absence> absences = new ArrayList<>();

      if (titre == null)
        absenceRepository.findAll().forEach(absences::add);
      else
        absenceRepository.findByTitreContaining(titre).forEach(absences::add);

      if (absences.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(absences, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/Absences/{id}")
  public ResponseEntity<Absence> getAbsenceById(@PathVariable("id") String id) {
    Optional<Absence> absenceData = absenceRepository.findById(id);

    if (absenceData.isPresent()) {
      return new ResponseEntity<>(absenceData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/Absences")
  public ResponseEntity<Absence> createAbsence(@RequestBody Absence absence) {
    try {
      Absence _absence = absenceRepository.save(absence);
      return new ResponseEntity<>(_absence, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/Absences/{id}")
  public ResponseEntity<Absence> updateAbsence(@PathVariable("id") String id, @RequestBody Absence absence) {
    Optional<Absence> absenceData = absenceRepository.findById(id);

    if (absenceData.isPresent()) {
      Absence _absence = absenceData.get();
      _absence.setTitre(absence.getTitre()); // Mise à jour ici
      _absence.setDescription(absence.getDescription()); // Mise à jour ici
      _absence.setEtat(absence.getEtat()); // Mise à jour ici
      _absence.setDatedebut(absence.getDatedebut()); // Mise à jour ici
      _absence.setDatefin(absence.getDatefin()); // Mise à jour ici
      _absence.setDatecreation(absence.getDatecreation()); // Mise à jour ici
      return new ResponseEntity<>(absenceRepository.save(_absence), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Absences/{id}")
  public ResponseEntity<HttpStatus> deleteAbsence(@PathVariable("id") String id) {
    try {
      absenceRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/Absences")
  public ResponseEntity<HttpStatus> deleteAllAbsences() {
    try {
      absenceRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
