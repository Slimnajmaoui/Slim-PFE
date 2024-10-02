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
import com.example.demo.model.Affectationgroupe;
import com.example.demo.model.Affectationprojet;
import com.example.demo.model.Projet;
import com.example.demo.model.User;
import com.example.demo.repository.AffectationgroupeRepository;
import com.example.demo.repository.AffectationprojetRepository;
import com.example.demo.repository.ProjetRepository;
import com.example.demo.repository.UserRepository;

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
public class AffectationprojetController {

  @Autowired
  AffectationprojetRepository AffectationprojetRepository;

  @GetMapping("/Affectationprojet")
  public ResponseEntity<List<Affectationprojet>> getAllAffectationprojet(@RequestParam(required = false) String nom) {
    try {
        List<Affectationprojet> Affectationprojet = new ArrayList<Affectationprojet>();

        if (nom == null)
        	AffectationprojetRepository.findAll().forEach(Affectationprojet::add);
        else
        	AffectationprojetRepository.findBynomContaining(nom).forEach(Affectationprojet::add);

        if (Affectationprojet.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Affectationprojet, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Affectationprojet/{id}")
  public ResponseEntity<Affectationprojet> getAffectationprojetById(@PathVariable("id") String id) {
    Optional<Affectationprojet> AffectationprojetData = AffectationprojetRepository.findById(id);

    if (AffectationprojetData.isPresent()) {
      return new ResponseEntity<>(AffectationprojetData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
@Autowired
UserRepository userrepos ; 
@Autowired
ProjetRepository projetrepos ; 
  @PostMapping("/Affectationprojet")
  public ResponseEntity<Affectationprojet> createAffectationgroupe(@RequestBody Affectationprojet Affectationprojet,String id,String idprojet) {
	  User u = this.userrepos.findById(id).get();
	  Projet p =this.projetrepos.findById(idprojet).get();
	  Affectationprojet.setiduser(u);
	  Affectationprojet.setProjet(p);
    try {
    	Affectationprojet _Affectationprojet = AffectationprojetRepository.save(Affectationprojet);
        return new ResponseEntity<>(_Affectationprojet, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
@GetMapping("affectationprojetbyuser")
public List<Affectationprojet> affectationprojetbyuser(String iduser){
	User u = this.userrepos.findById(iduser).get();
	return this.AffectationprojetRepository.findByUser(u);
}
  @PutMapping("/Affectationprojet/{id}")
  public ResponseEntity<Affectationprojet> updateAffectationprojet(@PathVariable("id") String id, @RequestBody Affectationprojet Affectationprojet) {
    Optional<Affectationprojet> AffectationprojetData = AffectationprojetRepository.findById(id);

    if (AffectationprojetData.isPresent()) {
    	Affectationprojet _Affectationprojet = AffectationprojetData.get();
    	_Affectationprojet.setnom(Affectationprojet.getnom());
    	_Affectationprojet.setdescription(Affectationprojet.getdescription());
    	_Affectationprojet.setetat(Affectationprojet.getetat());
    	_Affectationprojet.setiduser(Affectationprojet.getuser());
    	_Affectationprojet.setdatecreation(Affectationprojet.getdatecreation());
    	_Affectationprojet.setdatemodification(Affectationprojet.getdatemodification());
      return new ResponseEntity<>(AffectationprojetRepository.save(_Affectationprojet), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Affectationprojet/{id}")
  public ResponseEntity<HttpStatus> deleteAffectationgroupe(@PathVariable("id") String id) {
    try {
    	AffectationprojetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Affectationprojet")
  public ResponseEntity<HttpStatus> deleteAllAffectationprojet() {
    try {
    	AffectationprojetRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}