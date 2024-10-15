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
import com.example.demo.model.Affectationtache;
import com.example.demo.model.Tache;
import com.example.demo.model.User;
import com.example.demo.repository.AffectationgroupeRepository;
import com.example.demo.repository.AffectationprojetRepository;
import com.example.demo.repository.AffectationtacheRepository;
import com.example.demo.repository.TacheRepository;
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
public class AffectationtacheController {

  @Autowired
  AffectationtacheRepository AffectationtacheRepository;

  @GetMapping("/Affectationtache")
  public ResponseEntity<List<Affectationtache>> getAllAffectationtache(@RequestParam(required = false) String nom) {
    try {
        List<Affectationtache> Affectationtache = new ArrayList<Affectationtache>();

        if (nom == null)
        	AffectationtacheRepository.findAll().forEach(Affectationtache::add);
        else
        	AffectationtacheRepository.findBynomContaining(nom).forEach(Affectationtache::add);

        if (Affectationtache.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(Affectationtache, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/Affectationtache/{id}")
  public ResponseEntity<Affectationtache> getAffectationtacheById(@PathVariable("id") String id) {
    Optional<Affectationtache> AffectationtacheData = AffectationtacheRepository.findById(id);

    if (AffectationtacheData.isPresent()) {
      return new ResponseEntity<>(AffectationtacheData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
@Autowired
UserRepository userrepos ; 
@Autowired
TacheRepository tacherepos ;  
  @PostMapping("/AffectationtacheData")
  public ResponseEntity<Affectationtache> createAffectationtache(@RequestBody Affectationtache Affectationtache,String id,String idtache) {
	  User u = this.userrepos.findById(id).get();
	  Tache aff = this.tacherepos.findById(idtache).get();
	  Affectationtache.setTache(aff);
	  Affectationtache.setiduser(u);
    try {
    	Affectationtache _Affectationtache = AffectationtacheRepository.save(Affectationtache);
        return new ResponseEntity<>(_Affectationtache, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("affectationtachebyuser")
  public List<Affectationtache> affectationtachebyuser(String iduser){
	  User u = this.userrepos.findById(iduser).get();
	  return this.AffectationtacheRepository.findByUser(u);
	  
  }
  @PutMapping("/Affectationtache")
  public ResponseEntity<Affectationtache> updateAffectationtache(String id, @RequestBody Affectationtache Affectationtache,String iduser,String idtache) {
    Affectationtache AffectationtacheData = AffectationtacheRepository.findById(id).get();
	  User u = this.userrepos.findById(iduser).get();
	  Tache aff = this.tacherepos.findById(idtache).get();

    if (AffectationtacheData!=null) {
    	AffectationtacheData.setnom(Affectationtache.getnom());
    	AffectationtacheData.setdatecreation(Affectationtache.getdatecreation());
    	AffectationtacheData.setDatedebut(Affectationtache.getDatedebut());
    	AffectationtacheData.setDatefin(Affectationtache.getDatefin());
    	AffectationtacheData.setdatemodification(Affectationtache.getdatemodification());
    	AffectationtacheData.setdescription(Affectationtache.getdescription());
    	AffectationtacheData.setetat(Affectationtache.getetat());
    	AffectationtacheData.setiduser(u);
    	AffectationtacheData.setTache(aff);
    	return new ResponseEntity<>(AffectationtacheRepository.save(AffectationtacheData), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Affectationtache/{id}")
  public ResponseEntity<HttpStatus> deleteAffectationtache(@PathVariable("id") String id) {
    try {
    	AffectationtacheRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Affectationtache")
  public ResponseEntity<HttpStatus> deleteAllAffectationtache() {
    try {
    	AffectationtacheRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}