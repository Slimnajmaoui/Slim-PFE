package com.example.demo.controller;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.role;
import com.example.demo.repository.RoleRopository;


@RestController
@RequestMapping("/role")
public class RoleController {
@Autowired
RoleRopository rolerepos ; 
@PostMapping("/ajouter")	
public ResponseEntity<role> createUser(@RequestBody role r) {
    try {
        role ro = rolerepos.save(r);
        return new ResponseEntity<>(ro, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

@Autowired
private MongoOperations mongoOperations;

public RoleController(MongoOperations mongoOperations) {
    this.mongoOperations = mongoOperations;
}



}
