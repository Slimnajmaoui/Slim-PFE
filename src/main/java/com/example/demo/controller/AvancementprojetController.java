package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Affectationprojet;
import com.example.demo.model.Avancementprojet;
import com.example.demo.repository.AffectationprojetRepository;
import com.example.demo.repository.AvancementprojetRepository;

import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AvancementprojetController {

  @Autowired
  AvancementprojetRepository AvancementprojetRepository;

  @GetMapping("/Avancementprojets")
  public ResponseEntity<List<Avancementprojet>> getAllAvancementprojets(@RequestParam(required = false) String nom) {
    try {
        List<Avancementprojet> Avancementprojets = new ArrayList<Avancementprojet>();

        if (nom == null)
          AvancementprojetRepository.findAll().forEach(Avancementprojets::add);
        else
          AvancementprojetRepository.findBynomContaining(nom).forEach(Avancementprojets::add);

        if (Avancementprojets.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Avancementprojets, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Avancementprojets/{id}")
  public ResponseEntity<Avancementprojet> getAvancementprojetById(@PathVariable("id") String id) {
    Optional<Avancementprojet> AvancementprojetData = AvancementprojetRepository.findById(id);

    if (AvancementprojetData.isPresent()) {
      return new ResponseEntity<>(AvancementprojetData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
@Autowired
AffectationprojetRepository affectationprojetrepos ; 
  @PostMapping("/Avancementprojets")
  public ResponseEntity<Avancementprojet> createAvancementprojet(@RequestBody Avancementprojet Avancementprojet,String idprojet) {
  Affectationprojet affec = this.affectationprojetrepos.findById(idprojet).get();
  Avancementprojet.setAffectationprojet(affec);
  
	  try {
        Avancementprojet _Avancementprojet = AvancementprojetRepository.save(Avancementprojet);
        return new ResponseEntity<>(_Avancementprojet, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Avancementprojet")
  public ResponseEntity<Avancementprojet> updateAvancementprojet( String id, @RequestBody Avancementprojet Avancementprojet,String idprojet) {
    Avancementprojet AvancementprojetData = AvancementprojetRepository.findById(id).get();
    Affectationprojet affec = this.affectationprojetrepos.findById(idprojet).get();

    if (AvancementprojetData!=null) {
      AvancementprojetData.setnom(Avancementprojet.getnom());
      AvancementprojetData.setdescription(Avancementprojet.getdescription());
      AvancementprojetData.setdatedebut(Avancementprojet.getdatedebut());
      AvancementprojetData.setdatefin(Avancementprojet.getdatefin());
      AvancementprojetData.setetat(Avancementprojet.getetat());
      AvancementprojetData.setavancement(Avancementprojet.getavancement());
      AvancementprojetData.setdatecreation(Avancementprojet.getdatecreation());
      AvancementprojetData.setAffectationprojet(affec);
      return new ResponseEntity<>(AvancementprojetRepository.save(AvancementprojetData), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Avancementprojets/{id}")
  public ResponseEntity<HttpStatus> deleteAvancementprojet(@PathVariable("id") String id) {
    try {
        AvancementprojetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Avancementprojets")
  public ResponseEntity<HttpStatus> deleteAllAvancementprojets() {
    try {
        AvancementprojetRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}