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

import com.example.demo.model.Projet;
import com.example.demo.model.Tache;
import com.example.demo.repository.ProjetRepository;
import com.example.demo.repository.TacheRepository;

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
public class TacheController {

  @Autowired
  TacheRepository TacheRepository;

  @GetMapping("/Taches")
  public ResponseEntity<List<Tache>> getAllTaches(@RequestParam(required = false) String nom) {
    try {
        List<Tache> Taches = new ArrayList<Tache>();

        if (nom == null)
          TacheRepository.findAll().forEach(Taches::add);
        else
          TacheRepository.findBynomContaining(nom).forEach(Taches::add);

        if (Taches.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Taches, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Taches/{id}")
  public ResponseEntity<Tache> getTacheById(@PathVariable("id") String id) {
    Optional<Tache> TacheData = TacheRepository.findById(id);

    if (TacheData.isPresent()) {
      return new ResponseEntity<>(TacheData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
@Autowired
ProjetRepository projetrepos ;
  @PostMapping("/Taches")
  public ResponseEntity<Tache> createTache(@RequestBody Tache Tache,String idprojet) {
	  Projet p = this.projetrepos.findById(idprojet).get();
    try {
    	Tache.setIdprojet(p);
        Tache _Tache = TacheRepository.save(Tache);
        return new ResponseEntity<>(_Tache, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Taches")
  public ResponseEntity<Tache> updateTache(String id, @RequestBody Tache Tache,String idprojet) {
    Optional<Tache> TacheData = TacheRepository.findById(id);
	  Projet p = this.projetrepos.findById(idprojet).get();

    if (TacheData.isPresent()) {
      Tache _Tache = TacheData.get();
      _Tache.setNom(Tache.getNom());
      _Tache.setDescription(Tache.getDescription());
      _Tache.setDatedebut(Tache.getDatedebut());
      _Tache.setDatecreation(Tache.getDatecreation());
      _Tache.setDatefin(Tache.getDatefin());
      _Tache.setDatemodification(Tache.getDatemodification());
      _Tache.setEtat(Tache.getEtat());
      _Tache.setIdprojet(p);
     
      return new ResponseEntity<>(TacheRepository.save(_Tache), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Taches/{id}")
  public ResponseEntity<HttpStatus> deleteTache(@PathVariable("id") String id) {
    try {
        TacheRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Taches")
  public ResponseEntity<HttpStatus> deleteAllTaches() {
    try {
        TacheRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}