package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Affectationgroupe;
import com.example.demo.model.User;

public interface AffectationgroupeRepository extends MongoRepository<Affectationgroupe, String> {
  List<Affectationgroupe> findBynomContaining(String title);
  List<Affectationgroupe> findBynom(String nom);
List<Affectationgroupe> findByUser(User u);
}