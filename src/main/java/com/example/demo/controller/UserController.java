package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Absence;
import com.example.demo.model.User;
import com.example.demo.model.role;
import com.example.demo.repository.RoleRopository;
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
public class UserController {

  @Autowired
  UserRepository UserRepository;

  @GetMapping("/Users")
  public ResponseEntity<List<User>> getAllUser(@RequestParam(required = false) String nom) {
	 	List<User> us = this.UserRepository.findAll();
	  try {
		    List<User> user = new ArrayList<User>();

		    if (nom == null) {
		    	List<User> uss = this.UserRepository.findAll();
		    	UserRepository.findAll().forEach(user::add);
		    	
		    }
		    else
		    	UserRepository.findBynomContaining(nom).forEach(user::add);

		    if (user.isEmpty()) {

		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(user, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
  }

  @GetMapping("/Users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
    Optional<User> UserData = UserRepository.findById(id);

    if (UserData.isPresent()) {
      return new ResponseEntity<>(UserData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  RoleRopository rolerepos ; 
  @GetMapping("/Userbyprofil")
  public List<User> userbyprofil(String profil){
  	role r =this.rolerepos.findByProfil(profil);
  	return this.UserRepository.findByRole(r);

  }
  
  @PostMapping("/Users")
  public ResponseEntity<User> createUser(@RequestBody User User,String profil) {
    try {
    	role r =this.rolerepos.findByProfil(profil);
    	String pass = encoder.encode(User.getMdp());
    	User.setMdp(pass);
    	User.setRole(r);
        User _User = UserRepository.save(User);
        return new ResponseEntity<>(_User, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @PutMapping("/Users")
  public ResponseEntity<User> updateUser( String id,String email) {
    User UserData = UserRepository.findById(id).get();

    if (UserData!=null) {
    	UserData.setEmail(email);
    	
      return new ResponseEntity<>(UserRepository.save(UserData), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/Users/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    try {
        UserRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/Users")
  public ResponseEntity<HttpStatus> deleteAllUsers() {
    try {
        UserRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }



}