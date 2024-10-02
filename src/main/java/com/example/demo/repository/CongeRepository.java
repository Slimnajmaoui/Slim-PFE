package com.example.demo.repository;






import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Conge;
import com.example.demo.model.User;

public interface CongeRepository extends MongoRepository<Conge, String> {
  List<Conge> findBytitreContaining(String title);
  List<Conge> findBytitre(String titre);
List<Conge> findByUser(User u);
}