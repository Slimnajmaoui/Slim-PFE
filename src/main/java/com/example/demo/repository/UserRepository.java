package com.example.demo.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;
import com.example.demo.model.role;

public interface UserRepository extends MongoRepository<User, String> {
  List<User> findBynomContaining(String nom);
  List<User> findBynom(String nom);
User findByEmail(String email);
List<User> findByRole(role r);
}